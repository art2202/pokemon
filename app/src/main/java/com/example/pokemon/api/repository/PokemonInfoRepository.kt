package com.example.pokemon.api.repository

import com.example.pokemon.api.RestApi
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.domain.Pokemon
import retrofit2.Response

class PokemonInfoRepository(private val restApi: RestApi) {

    suspend fun getInfoPokemon(nomePokemon : String): Response<PokemonDataResponse> {


        try{
            val requisicao = restApi.getApiService().getInfoPokemon("pokemon/" + nomePokemon)

            if(!requisicao.isSuccessful){
                throw Exception("Erro ao fazer requisição das informações do pokemon")
            }
            else
                return requisicao

        }
        catch (e : Exception){
            println(e.message + "info repository")
            throw Exception(e.message)
        }
    }

    fun mapResponse(response : Response<PokemonDataResponse>) : Pokemon{

        return Pokemon(
            response.body()!!.abilities,
            response.body()!!.base_experience,
            response.body()!!.forms,
            response.body()!!.game_indices,
            response.body()!!.height,
            response.body()!!.held_items,
            response.body()!!.id,
            response.body()!!.is_default,
            response.body()!!.moves,
            response.body()!!.name,
            response.body()!!.order,
            response.body()!!.species,
            response.body()!!.stats,
            response.body()!!.types,
            response.body()!!.weight
        )

    }
}