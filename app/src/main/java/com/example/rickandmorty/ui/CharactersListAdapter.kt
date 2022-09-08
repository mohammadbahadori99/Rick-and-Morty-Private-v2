package com.example.rickandmorty.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickandmorty.R
import com.example.rickandmorty.model.CharacterView
import kotlinx.android.synthetic.main.character_item.view.*

class CharactersListAdapter(private val onClick: (CharacterView) -> Unit) :
    PagingDataAdapter<CharacterView, CharactersListAdapter.CharacterViewHolder>(CharacterDiffCallback) {
    class CharacterViewHolder(itemView: View, val onClick: (CharacterView) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private var characterView: CharacterView? = null

        init {
            itemView.setOnClickListener {
                characterView?.let {
                    onClick(it)
                }
            }
        }

        fun bind(data: CharacterView) {
            characterView = data
            itemView.tv_name.text = data.name
            itemView.tv_status.text = data.status
            itemView.tv_gender.text = data.gender
            itemView.tv_locationName.text = data.locationName
            Glide.with(itemView.context).load(data.image)
                .placeholder(R.drawable.place_holder).fitCenter().into(itemView.iv_characterImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val character = getItem(position)
        character?.let{
            holder.bind(it)
        }
    }
}


object CharacterDiffCallback : DiffUtil.ItemCallback<CharacterView>() {
    override fun areItemsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: CharacterView, newItem: CharacterView): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}