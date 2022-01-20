package com.sadikahmetozdemir.sadik_fodamy.ui.intro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.sadikahmetozdemir.data.shared.local.IntroModel
import com.sadikahmetozdemir.sadik_fodamy.databinding.ItemIntroBinding

class IntroAdapter(val list: ArrayList<IntroModel>) : RecyclerView.Adapter<IntroViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        return IntroViewHolder(ItemIntroBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: IntroViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
class IntroViewHolder(val binding: ItemIntroBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(intro: IntroModel) {
        binding.textViewTitle.text = intro.tittle
        binding.textViewDesc.text = intro.description
        intro.drawableId?.let {
            binding.introImage1.setImageDrawable(ContextCompat.getDrawable(binding.root.context,
                intro.drawableId!!
            ))
        } ?: kotlin.run {
            binding.introImage1.visibility = View.GONE
        }
    }
}
