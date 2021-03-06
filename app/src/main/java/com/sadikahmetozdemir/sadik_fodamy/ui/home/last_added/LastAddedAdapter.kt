package com.sadikahmetozdemir.sadik_fodamy.ui.home.last_added

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemHomeBinding
import javax.inject.Inject

class LastAddedAdapter @Inject constructor() :
    PagingDataAdapter<Recipe, LastAddedAdapter.ViewHolder>(
        recipeComparator
    ) {

    var itemClicked: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: LastAddedAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LastAddedAdapter.ViewHolder {

        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder((binding))
    }
    inner class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            binding.apply {
                binding.foodImage.setOnClickListener {
                    item.id?.let { it1 -> itemClicked?.invoke(it1) }
                }
                tvUsername.text = item.user?.username
                tvFoodTitle.text = item.title
                tvFoodDescription.text = item.categoryModel?.name
                tvComment.text = String.format(
                    binding.root.context.getString(R.string.comment),
                    item.commentCount
                )
                tvLike.text =
                    String.format(binding.root.context.getString(R.string.like), item.likeCount)

                tvRecipe.text = String.format(
                    binding.root.context.getString(R.string.recipe),
                    item.user?.recipeCount
                )
                tvFollower.text = String.format(
                    binding.root.context.getString(R.string.follower),
                    item.user?.followingCount
                )
                Glide
                    .with(binding.root.context)
                    .load(item.user?.image?.url)
                    .into(ivUser)
                Glide
                    .with(binding.root.context)
                    .load(item.images?.get(0)?.url)
                    .into(foodImage)

                editorChoiceMedal.isVisible = (item.isEditorChoice == true)
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
