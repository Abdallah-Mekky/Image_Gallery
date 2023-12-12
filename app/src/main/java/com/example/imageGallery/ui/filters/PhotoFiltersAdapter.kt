package com.example.imageGallery.ui.filters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.imageGallery.databinding.PhotoFilterItemBinding
import com.example.imageGallery.model.PhotoFilterItem

class PhotoFiltersAdapter : Adapter<PhotoFiltersAdapter.PhotoFiltersViewHolder>() {


    private var photoFiltersList: List<PhotoFilterItem>? = null
    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoFiltersViewHolder {
        val itemBinding =
            PhotoFilterItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoFiltersViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: PhotoFiltersViewHolder,
        position: Int
    ) {
        val currentPhotoFilter = photoFiltersList?.get(position)
        holder.bindData(currentPhotoFilter)

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener {
                currentPhotoFilter?.let { photo ->
                    onItemClickListener?.onItemClick(
                        position,
                        photo
                    )
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return photoFiltersList?.size ?: 0
    }


    interface OnItemClickListener {
        fun onItemClick(
            position: Int,
            currentPhotoFilter: PhotoFilterItem
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    fun refreshAdapter(
        photoFiltersList: List<PhotoFilterItem>
    ) {
        this.photoFiltersList = photoFiltersList
        notifyDataSetChanged()
    }

    class PhotoFiltersViewHolder(
        private val binding: PhotoFilterItemBinding
    ) : ViewHolder(binding.root) {

        fun bindData(currentPhotoFilter: PhotoFilterItem?) {
            binding.apply {
                filterNameTV.text = currentPhotoFilter?.filterName
                filterIV.setImageBitmap(currentPhotoFilter?.photoFilter)
            }
        }
    }
}