package com.example.imageGallery.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.domain.model.api.reponse.PhotoDTO
import com.example.imageGallery.R
import com.example.imageGallery.databinding.RandomPhotoItemBinding

class RandomPhotosAdapter : PagingDataAdapter<PhotoDTO, RandomPhotosAdapter.RandomPhotosViewHolder>(
    diffCallBack
) {


    companion object {

        val diffCallBack = object : DiffUtil.ItemCallback<PhotoDTO>() {
            override fun areItemsTheSame(oldItem: PhotoDTO, newItem: PhotoDTO): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: PhotoDTO, newItem: PhotoDTO): Boolean {
                return oldItem == newItem
            }

        }
    }

    var onItemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RandomPhotosViewHolder {
        val itemBinding =
            RandomPhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RandomPhotosViewHolder(itemBinding)
    }

    override fun onBindViewHolder(
        holder: RandomPhotosViewHolder,
        position: Int
    ) {
        val currentRandomPhoto = getItem(position)
        holder.bindData(currentRandomPhoto)

        if (onItemClickListener != null) {
            holder.itemView.setOnClickListener {
                currentRandomPhoto?.let { photo ->
                    onItemClickListener?.onItemClick(
                        position,
                        photo
                    )
                }
            }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(
            position: Int,
            currentPhoto: PhotoDTO
        )
    }

    fun isEmpty(): Boolean {
        return itemCount == 0
    }

    class RandomPhotosViewHolder(private val binding: RandomPhotoItemBinding) :
        ViewHolder(binding.root) {

        fun bindData(currentRandomPhoto: PhotoDTO?) {
            loadAndCacheImageByGlide(currentRandomPhoto)
        }

        private fun loadAndCacheImageByGlide(
            currentRandomPhoto: PhotoDTO?
        ) {
            //caching image
            val glideOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(false)
                .placeholder(R.drawable.ic_loading)
                .fitCenter()


            //load image
            Glide.with(this.itemView)
                .setDefaultRequestOptions(glideOptions).also {
                    it.load(currentRandomPhoto?.src?.medium)
                        .into(binding.randomPhotoIV)
                }
        }
    }
}