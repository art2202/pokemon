package com.example.pokemon.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("pokemon/?limit=100")
    fun pokemons() : Call<List<Any>>
}