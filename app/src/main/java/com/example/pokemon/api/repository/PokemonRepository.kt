package com.example.pokemon.api.repository

import com.example.pokemon.api.RestApi
import com.example.pokemon.api.model.NamePokemonDataResponse
import com.example.pokemon.api.model.PokemonListResponse
import retrofit2.Response

class PokemonRepository(private val api: RestApi) {

    suspend fun getListaPokemon(offset : Int) : Response<PokemonListResponse> {

        try{
            val response = api.getApiService().getPokemons("pokemon/?offset=${offset}&limit=100")
//            response.body()!!.results[0].url
            if(!response.isSuccessful)
                throw Exception("Erro ao fazer requisição")

            else
                return response

        }
        catch (e : Exception){
            println(e.message)
            throw Exception(e.message)
        }

    }

    fun mapResponse(pokemonsResponse : Response<PokemonListResponse>) : List<NamePokemonDataResponse>?{
        return  pokemonsResponse.body()?.results?.map {
            NamePokemonDataResponse(
                it.name ?: "",
                pegaId(it.url) ?: ""
            )
        }

    }
    fun pegaId(url : String?) : String? = url?.removePrefix("https://pokeapi.co/api/v2/pokemon/")?.removeSuffix("/")

}