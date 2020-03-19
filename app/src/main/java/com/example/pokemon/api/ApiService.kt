package com.example.pokemon.api

import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.api.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemons() : Response<PokemonListResponse>
}