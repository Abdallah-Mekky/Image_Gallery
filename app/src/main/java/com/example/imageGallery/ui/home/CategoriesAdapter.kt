package com.example.imageGallery.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.imageGallery.databinding.CategoryItemBinding
import com.example.imageGallery.model.CategoryItem

class CategoriesAdapter : Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    private var categoriesList: List<CategoryItem>? = null
    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoriesViewHolder {
        val itemBinding =
            CategoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: CategoriesViewHolder,
        position: Int
    ) {
        val currentCategory = categoriesList?.get(position)
        holder.bindData(currentCategory)

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener {
                currentCategory?.let { photo -> onItemClickListener?.onItemClick(position, photo) }
            }
        }
    }

    override fun getItemCount(): Int {
        return categoriesList?.size ?: 0
    }


    interface OnItemClickListener {
        fun onItemClick(
            position: Int,
            currentCategory: CategoryItem
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAdapter(
        categoriesList: List<CategoryItem>
    ) {
        this.categoriesList = categoriesList
        notifyDataSetChanged()
    }

    class CategoriesViewHolder(private val binding: CategoryItemBinding) :
        ViewHolder(binding.root) {

        fun bindData(currentCategory: CategoryItem?) {
            binding.apply {
                categoryIV.setImageResource(currentCategory?.categoryImage ?: 0)
                categoryTV.text = currentCategory?.categoryTitle
            }
        }

    }
}