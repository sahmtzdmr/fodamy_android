package com.sadikahmetozdemir.sadik_fodamy.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.sadik_fodamy.databinding.CustomImagePopupBinding
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiceModel
import com.sadikahmetozdemir.sadik_fodamy.shared.remote.EditorChoiseResponseModel
import com.sadikahmetozdemir.sadik_fodamy.utils.extensions.load
import retrofit2.Retrofit

class RecipeImageAdapter(val list: ArrayList<String>) :
    RecyclerView.Adapter<RecipeImageAdapter.ImageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ImageViewHolder(CustomImagePopupBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
       holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class ImageViewHolder(val binding: CustomImagePopupBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(recipeImageURL: String) {
            binding.ivDetailFood.load(url = recipeImageURL)


        }
        }




}