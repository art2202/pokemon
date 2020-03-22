package com.example.pokemon.api.model

class PokemonListResponse(val count: Int,
                          val next: String?,
                          val previous: String?,
                          val results: List<NamePokemonDataResponse>?)