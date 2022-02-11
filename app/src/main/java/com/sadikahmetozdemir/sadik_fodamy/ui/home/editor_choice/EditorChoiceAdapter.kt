package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemHomeBinding
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class EditorChoiceAdapter @Inject constructor() :
    PagingDataAdapter<Recipe, EditorChoiceAdapter.ViewHolder>(recipeComparator) {

    var itemClicked: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem?.let {
            holder.bind(it)
        }
    }

    inner class ViewHolder(val binding: ItemHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
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
                ivUser.loadCircleCrop(url = item.user?.image?.url)

                foodImage.load(url = item.images?.get(0)?.url)

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
