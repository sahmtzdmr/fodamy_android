package com.sadikahmetozdemir.sadik_fodamy.ui.home.editor_choice

import android.graphics.drawable.Drawable
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemHomeBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.ui.home.ItemDetailsClickedListener
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.gone
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop

import javax.inject.Inject

class EditorChoiceAdapter @Inject constructor() :
    PagingDataAdapter<EditorChoiceModel, EditorChoiceAdapter.ViewHolder>(recipeComparator) {
private lateinit var itemDetailsClickedListener: ItemDetailsClickedListener
fun setListener(itemDetailsClickedListener: ItemDetailsClickedListener){
    this.itemDetailsClickedListener=itemDetailsClickedListener
}
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
            binding?.foodImage.setOnClickListener {

                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val currentItem = getItem(position)
                    currentItem?.let {
                        it.id?.let { it1 -> itemDetailsClickedListener.onItemClicked(it1) }
                    }
                }


            }

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
                ivUser.loadCircleCrop(url = item.user?.image?.url)

               foodImage.load(url = item.images?.get(0)?.url)


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
