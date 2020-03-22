package com.example.pokemon.api

import com.example.pokemon.api.model.AbilityDescriptionDataResponse
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.api.model.PokemonListResponse
import com.example.pokemon.api.model.TypeListDataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
    suspend fun getPokemons(@Url url : String) : Response<PokemonListResponse>
    @GET
    suspend fun getInfoPokemon(@Url url :String) : Response<PokemonDataResponse>
    @GET
    suspend fun getHabilidadesPokemon(@Url url : String) : Response<AbilityDescriptionDataResponse>
    @GET
    suspend fun getPokemonByType(@Url url : String) : Response<TypeListDataResponse>
}