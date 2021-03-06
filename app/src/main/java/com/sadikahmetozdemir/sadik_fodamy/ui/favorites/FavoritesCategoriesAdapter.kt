package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemFavoritesDetailBinding
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class FavoritesCategoriesAdapter @Inject constructor() :
    PagingDataAdapter<Recipe, FavoritesCategoriesAdapter.ViewHolder>(
        recipeComparator
    ) {

    var itemClickedToImages: ((Recipe) -> Unit)? = null

    override fun onBindViewHolder(holder: FavoritesCategoriesAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesCategoriesAdapter.ViewHolder {
        val binding =
            ItemFavoritesDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    inner class ViewHolder(val binding: ItemFavoritesDetailBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            binding.apply {

                ivUser.loadCircleCrop(url = item.user?.image?.url)
                tvUsername.text = item.user?.name
                tvRecipe.text = String.format(
                    binding.root.context.getString(R.string.recipe),
                    item.user?.recipeCount
                )
                tvFollower.text = String.format(
                    binding.root.context.getString(R.string.follower),
                    item.user?.followingCount
                )
                tvFoodTitle.text = item.title
                tvFoodDescription.text = item.categoryModel?.name
                foodImage.load(url = item.images?.get(0)?.url)
                editorChoiceMedal.isVisible = (item.isEditorChoice == true)
                tvComment.text = String.format(
                    binding.root.context.getString(R.string.comment),
                    item.commentCount
                )
                tvLike.text =
                    String.format(binding.root.context.getString(R.string.like), item.likeCount)
                foodImage.setOnClickListener {
                    itemClickedToImages?.invoke(item)
                }
            }
        }
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
