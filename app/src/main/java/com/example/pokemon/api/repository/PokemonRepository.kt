package com.example.pokemon.api.repository

import com.example.pokemon.api.ApiService
import com.example.pokemon.api.RestApi
import com.example.pokemon.api.model.PokemonDataResponse
import retrofit2.Retrofit

class PokemonRepository(private val api: RestApi) {

    suspend fun getListaPokemon(offset : Int) : List<PokemonDataResponse>?{
        var pokemonsResponse: List<PokemonDataResponse>? = listOf()

        try{

            val response = api.getApiService().getPokemons("pokemon/?offset=${offset}&limit=100")


            if(response.isSuccessful){

                val listagem = response.body()

                pokemonsResponse = listagem?.results?.map { PokemonDataResponse(it.name ?: "", it.url ?: "") }
            }
        }
        catch (e : Exception){
            throw Exception(e.message)
        }

        return pokemonsResponse ?: listOf()
    }

}