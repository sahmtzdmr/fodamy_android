package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.sadik_fodamy.R
import com.sadikahmetozdemir.sadik_fodamy.databinding.FavoritesChildItemBinding
import com.sadikahmetozdemir.domain.entities.Recipe
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop

class FavoritesChildAdapter(private var categoryItem: List<Recipe>) :
    RecyclerView.Adapter<FavoritesChildAdapter.CategoryViewHolder>() {

    var itemClicked: ((Int) -> Unit)? = null
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesChildAdapter.CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return CategoryViewHolder(FavoritesChildItemBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: FavoritesChildAdapter.CategoryViewHolder, position: Int) {
        holder.bind(categoryItem[position])
    }

    override fun getItemCount(): Int {
        return categoryItem.size
    }

    inner class CategoryViewHolder(val binding: FavoritesChildItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Recipe) {
            binding.apply {
                ivFavoritesFood.load(url = item.images?.get(0)?.url)
                ivUserImage.loadCircleCrop(url = item.user?.image?.url)
                tvUserName.text = item.user?.username
                tvFavoritesFoodTitle.text = item.title
                tvFavoritesComment.text = binding.root.context.getString(R.string.comment, item.commentCount)
                tvFavoritesLike.text = binding.root.context.getString(R.string.like,item.likeCount)
                ivFavoritesFood.setOnClickListener {
                    item.id?.let {
                        itemClicked?.invoke(it)
                    }
                }
            }
        }
    }
}
