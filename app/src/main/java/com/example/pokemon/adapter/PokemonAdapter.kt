package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.ui.EventoClick
import com.example.pokemon.api.model.NamePokemonDataResponse
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_pokemon.view.*



class PokemonAdapter(private var listaNamePokemonDataResponse: ArrayList<NamePokemonDataResponse>?,
                     private val context : Context,
                     private val eventoClick: EventoClick) : RecyclerView.Adapter<PokemonAdapter.MyViewHolder>() {

    private val picasso = Picasso.get()
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

    fun addLista(listaNamePokemon: List<NamePokemonDataResponse>?){
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false))
    }

    override fun getItemCount(): Int = listaNamePokemonDataResponse?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listaNamePokemonDataResponse!!.get(position)

        picasso.load("https://pokeres.bastionbot.org/images/pokemon/${position+1}.png").into(holder.itemView.foto_pokemon)
        holder.itemView.text_nome_pokemon.text = item.name
        holder.itemView.id_pokemon.text = (position+1).toString()
        holder.itemView.adapter_layout.setOnClickListener {
            eventoClick.clickMostrarInfo(item)
        }
    }
}