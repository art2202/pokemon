package com.example.pokemon.ui

import com.example.pokemon.api.model.NamePokemonDataResponse

interface EventoClick {

    fun clickMostrarInfo(item : NamePokemonDataResponse)
}