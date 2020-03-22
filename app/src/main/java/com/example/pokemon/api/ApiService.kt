package com.example.pokemon.api

import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.api.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPokemons(@Url url : String) : Response<PokemonListResponse>
    @GET
    suspend fun getInfoPokemon(@Url url :String) : Response<PokemonDataResponse>
}