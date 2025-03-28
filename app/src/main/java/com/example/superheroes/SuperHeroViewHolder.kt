package com.example.superheroes

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroes.databinding.ItemSuperheroBinding
import com.squareup.picasso.Picasso

class SuperHeroViewHolder(view:View): RecyclerView.ViewHolder(view) {

    private val binding = ItemSuperheroBinding.bind(view)

    fun bind(superHeroItemResponse: SuperHeroItemResponse, onItemSelected:(String) -> Unit){
        binding.tvName.text = superHeroItemResponse.name

        Picasso.get().load(superHeroItemResponse.image.url).into(binding.ivSuperhero)
        binding.root.setOnClickListener { onItemSelected(superHeroItemResponse.superheroId) }
    }
}