package com.sadikahmetozdemir.sadik_fodamy.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.sadik_fodamy.databinding.FavoritesItemCardviewBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.FavoritesCategoryModel
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.loadCircleCrop
import javax.inject.Inject

class FavoritesItemAdapter @Inject constructor():PagingDataAdapter<FavoritesCategoryModel,FavoritesItemAdapter.ViewHolder> (
    recipeComparator){
    override fun onBindViewHolder(holder: FavoritesItemAdapter.ViewHolder, position: Int) {
       val currentItem=getItem(position)
        currentItem.let {
            it?.let { it1 -> holder.bind(it1) }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritesItemAdapter.ViewHolder {
        val binding=FavoritesItemCardviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder((binding))
    }

    inner class ViewHolder(val binding: FavoritesItemCardviewBinding):RecyclerView.ViewHolder(binding.root){




        fun bind(item:FavoritesCategoryModel){


            binding.apply {

            tvUserName.text=item.recipes.get(0).user?.username
              //  ivUserImage.loadCircleCrop(url = item.image.url)
             //   ivFavoritesFood.load(url =item.image.url )
               // tvFavoritesFoodTitle.text=item.recipes.title
             //   tvFavoritesComment.text=item.recipes.comment_count.toString()
               // tvFavoritesLike.text=item.recipes.like_count.toString()


            }

        }


    }




    companion object {
        private val recipeComparator = object : DiffUtil.ItemCallback<FavoritesCategoryModel>() {
            override fun areItemsTheSame(
                oldItem: FavoritesCategoryModel,
                newItem: FavoritesCategoryModel
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: FavoritesCategoryModel,
                newItem: FavoritesCategoryModel
            ): Boolean =
                oldItem == newItem
        }
    }
}