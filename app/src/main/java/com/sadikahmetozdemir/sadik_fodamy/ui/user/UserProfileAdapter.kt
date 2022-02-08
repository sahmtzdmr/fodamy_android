package com.sadikahmetozdemir.sadik_fodamy.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FavoritesChildItemBinding
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class UserProfileAdapter @Inject constructor() : PagingDataAdapter<Recipe, UserProfileAdapter.ViewHolder>(
    recipeComparator
) {

    override fun onBindViewHolder(holder: UserProfileAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(private val binding: FavoritesChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            binding.ivUserImage.loadCircleCrop(true,item.user?.image?.url)
            binding.ivFavoritesFood.load(true,item.images?.get(0)?.url)
            binding.tvFavoritesFoodTitle.text=item.title
            binding.tvFavoritesComment.text=binding.root.context.getString(R.string.comment, item.commentCount)
            binding.tvFavoritesLike.text=binding.root.context.getString(R.string.like,item.likeCount)



        }
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserProfileAdapter.ViewHolder {
        val binding =
            FavoritesChildItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    companion object {
        private val recipeComparator = object : DiffUtil.ItemCallback<Recipe>() {
            override fun areItemsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Recipe,
                newItem: Recipe
            ): Boolean =
                oldItem == newItem
        }
    }

}