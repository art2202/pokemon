package com.example.pokemon.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemon.R
import com.example.pokemon.api.model.AbilitiesDataResponse
import com.example.pokemon.ui.clicks.MostrarHabilidades
import kotlinx.android.synthetic.main.item_habilidade.view.*

class MovesAdapter(private val context: Context,
                   private val habilidades : List<AbilitiesDataResponse>?,
                   private val mostrarHabilidades: MostrarHabilidades
) : RecyclerView.Adapter<MovesAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_habilidade, parent, false))
    }

    override fun getItemCount(): Int = habilidades?.size ?: 0

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = habilidades?.get(position)

        holder.itemView.nome_habilidade.text = item?.ability?.name!!.replace("-", " ")

        holder.itemView.nome_habilidade.setOnClickListener {
            mostrarHabilidades.listarTipo(item)
        }
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view)

}
