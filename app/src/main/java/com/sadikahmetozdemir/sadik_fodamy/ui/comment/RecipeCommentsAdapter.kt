package com.sadikahmetozdemir.sadik_fodamy.ui.comment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemCommentBinding
import com.sadikahmetozdemir.domain.entities.Comment
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class RecipeCommentsAdapter @Inject constructor() :
    PagingDataAdapter<Comment, RecipeCommentsAdapter.ViewHolder>(recipeComparator) {
    var itemClicked: ((Comment) -> Unit)? = null
    var imageClicked: ((Comment) -> Unit)? = null

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
            binding.ivUser.setOnClickListener {
                if (bindingAdapterPosition!=RecyclerView.NO_POSITION){
                    val currentItem=getItem(bindingAdapterPosition)
                    currentItem?.let {
                        imageClicked?.invoke(it)
                    }
                }
            }
        }
        fun bind(item: Comment) {
            binding.apply {
                ivUser.loadCircleCrop(url = item.user?.image?.url)
                tvUsername.text = item.user?.username
                tvComment.text = String.format(
                    binding.root.context.getString(R.string.comment),
                    item.user?.recipeCount
                )
                tvFollower.text = String.format(
                    binding.root.context.getString(R.string.follower),
                    item.user?.followingCount
                )
                tvTime.text = item.difference
                tvComment.text = item.text
            }
        }
    }

    companion object {
        private val recipeComparator = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(
                oldItem: Comment,
                newItem: Comment
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Comment,
                newItem: Comment
            ): Boolean =
                oldItem == newItem
        }
    }
}
