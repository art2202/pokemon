package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.api.model.TypesDataResponse
import com.example.pokemon.ui.clicks.ClickTipo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tipo.view.*

class TipoAdapter(val context: Context, val listaTipo : List<TypesDataResponse>?, val clickTipo: ClickTipo) : RecyclerView.Adapter<TipoAdapter.MyViewHolder>() {

    val picasso = Picasso.get()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_tipo, parent, false))

    }

    override fun getItemCount(): Int = listaTipo?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = listaTipo?.get(position)

        when(item?.type?.name){
            "normal" -> holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_normal)
            "fighting"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_fighting)
            "flying"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_flying)
            "poison"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_poison)
            "ground"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_ground)
            "rock"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_rock)
            "bug"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_bug)
            "ghost"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_ghost)
            "steel"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_steel)
            "fire"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_fire)
            "water"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_water)
            "grass"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_grass)
            "electric"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_electric)
            "psychic"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_psychic)
            "ice"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_ice)
            "dragon"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_dragon)
            "dark"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_dark)
            "fairy"->holder.itemView.imagem_tipo.setImageResource(R.drawable.ic_fairy)
            "shadow"->holder.itemView.imagem_tipo.setImageResource(R.drawable.shadow)
            "unknown"->holder.itemView.imagem_tipo.setImageResource(R.drawable.unknown)
        }

        holder.itemView.imagem_tipo.setOnClickListener {
            clickTipo.mostrarTipo(item?.type!!)
        }


    }


    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}
