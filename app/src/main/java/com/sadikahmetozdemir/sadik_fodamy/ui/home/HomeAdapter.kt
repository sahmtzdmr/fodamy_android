package com.sadikahmetozdemir.sadik_fodamy.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemHomeBinding
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemIntroBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel

import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiseResponseModel
import com.sadikahmetozdemir.sadik_fodamy.ui.intro.IntroViewHolder
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
    PagingDataAdapter<EditorChoiceModel, HomeAdapter.ViewHolder>(recipeComparator) {


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
        init {

        }

        fun bind(item: EditorChoiceModel) {
            binding.apply {
                tvUsername.text = item.user?.username
                tvFoodTitle.text = item.title
                tvFoodDescription.text = item.category?.name
                tvComment.text = String.format(
                    binding.root.context.getString(R.string.comment),
                    item.comment_count
                )
                tvLike.text =
                    String.format(binding.root.context.getString(R.string.like), item.like_count)

                tvRecipe.text = String.format(
                    binding.root.context.getString(R.string.recipe),
                    item.user?.recipe_count
                )
                tvFollower.text = String.format(
                    binding.root.context.getString(R.string.follower),
                    item.user?.following_count
                )
                Glide
                    .with(binding.root.context)
                    .load(item.user?.image?.url)
                    .into(ivUser)
                Glide
                    .with(binding.root.context)
                    .load(item.images?.get(0)?.url)
                    .into(foodImage)

                editorChoiceMedal.isVisible=(item.isEditorChoice==true)




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
