package com.quixada.ufc.projectx

import androidx.recyclerview.widget.RecyclerView
import com.quixada.ufc.projectx.databinding.CardCellBinding

class CardViewHolder(
    private val cardCellBinding: CardCellBinding,
    private val clickListener: CharacterClickListener
) : RecyclerView.ViewHolder(cardCellBinding.root)
{
    fun bindBook(character: Character)
    {
        cardCellBinding.cover.setImageResource(character.cover)
        cardCellBinding.title.text = character.title

        cardCellBinding.cardView.setOnClickListener{
            clickListener.onClick(character)
        }
    }
}