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
            response.body()!!.abilitiesDataResponse,
            response.body()!!.baseExperience,
            response.body()!!.formsDataResponse,
            response.body()!!.gameIndicesDataResponse,
            response.body()!!.height,
            response.body()!!.heldItemsDataResponse,
            response.body()!!.id,
            response.body()!!.isDefault,
            response.body()!!.movesDataResponse,
            response.body()!!.name,
            response.body()!!.order,
            response.body()!!.speciesDataResponse,
            response.body()!!.statsDataResponse,
            response.body()!!.typesDataResponse,
            response.body()!!.weight
        )

    }
}