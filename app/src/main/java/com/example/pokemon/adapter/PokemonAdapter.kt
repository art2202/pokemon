package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.api.model.PokemonDataResponse
import kotlinx.android.synthetic.main.item_pokemon.view.*

class PokemonAdapter(private var listaPokemonDataResponse: List<PokemonDataResponse>?, private val context : Context) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int = listaPokemonDataResponse?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listaPokemonDataResponse!!.get(position)

        holder.itemView.text_nome_pokemon.text = item.name
    }
}