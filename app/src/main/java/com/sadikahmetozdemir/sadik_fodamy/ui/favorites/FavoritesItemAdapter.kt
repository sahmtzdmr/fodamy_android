package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.domain.entities.Category
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemFavoritesBinding
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class FavoritesItemAdapter @Inject constructor() :
    PagingDataAdapter<Category, FavoritesItemAdapter.ViewHolder>(
        recipeComparator
    ) {

    var itemClicked: ((Category) -> Unit)? = null
    var childItemClicked: ((Int) -> Unit)? = null

    override fun onBindViewHolder(holder: FavoritesItemAdapter.ViewHolder, position: Int) {
        val currentItem = getItem(position)
        currentItem.let {
            it?.let { it1 -> holder.bind(it1) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesItemAdapter.ViewHolder {
        val binding =
            ItemFavoritesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder((binding))
    }

    inner class ViewHolder(val binding: ItemFavoritesBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Category) {
            val childAdapter = item.recipes?.let { FavoritesChildAdapter(it) }
            binding.favoritesRecylerview.apply {
                setHasFixedSize(true)
                adapter = childAdapter
            }

            childAdapter?.itemClicked = {
                childItemClicked?.invoke(it)
            }

            binding.apply {

                tvSeeAll.setOnClickListener {
                    //      var categoryID=item.id
                    itemClicked?.invoke(item)
                }

                ivTitleDrawable.loadCircleCrop(url = item.image?.url)
                tvFoodName.text = item.name
            }
        }
    }

    companion object {
        private val recipeComparator = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean =
                oldItem == newItem
        }
    }
}
