package com.example.pokemon.activity

import com.example.pokemon.api.model.PokemonDataResponse

interface EventoClick {

    fun clickMostrarInfo(item : PokemonDataResponse)
}