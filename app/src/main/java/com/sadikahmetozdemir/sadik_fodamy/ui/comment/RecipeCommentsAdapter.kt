package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemCommentBinding
import com.sadikahmetozdemir.data.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class RecipeCommentsAdapter @Inject constructor() :
    PagingDataAdapter<EditorChoiceModel, RecipeCommentsAdapter.ViewHolder>(recipeComparator) {
    var itemClicked: ((EditorChoiceModel) -> Unit)? = null

    override fun onBindViewHolder(holder: RecipeCommentsAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem.let {
            it?.let { it1 -> holder.bind(it1) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecipeCommentsAdapter.ViewHolder {
        val binding = ItemCommentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    inner class ViewHolder(val binding: ItemCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.tvComment.setOnLongClickListener {
                if (bindingAdapterPosition != RecyclerView.NO_POSITION) {
                    val currentItem = getItem(bindingAdapterPosition)
                    currentItem?.let {
                        itemClicked?.invoke(it)
                    }
                }
                false
            }
        }
        fun bind(item: EditorChoiceModel) {
            binding.apply {
                ivUser.loadCircleCrop(url = item.user?.image?.url)
                tvUsername.text = item.user?.username
                tvComment.text = String.format(
                    binding.root.context.getString(R.string.comment),
                    item.user?.recipe_count
                )
                tvFollower.text = String.format(
                    binding.root.context.getString(R.string.follower),
                    item.user?.following_count
                )
                tvTime.text = item.difference.toString()
                tvComment.text = item.text
            }
        }
    }

    companion object {
        private val recipeComparator = object : DiffUtil.ItemCallback<EditorChoiceModel>() {
            override fun areItemsTheSame(
                oldItem: EditorChoiceModel,
                newItem: EditorChoiceModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: EditorChoiceModel,
                newItem: EditorChoiceModel
            ): Boolean =
                oldItem == newItem
        }
    }
}
