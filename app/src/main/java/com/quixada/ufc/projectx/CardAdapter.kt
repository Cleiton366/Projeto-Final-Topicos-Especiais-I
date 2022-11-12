package com.quixada.ufc.projectx

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.quixada.ufc.projectx.databinding.CardCellBinding

class CardAdapter(
    private val characters : List<Character>,
    private val clickListener: CharacterClickListener
    )
    : RecyclerView.Adapter<CardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder
    {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellBinding.inflate(from, parent, false)
        return CardViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindBook(characters[position])
    }

    override fun getItemCount(): Int = characters.size
}