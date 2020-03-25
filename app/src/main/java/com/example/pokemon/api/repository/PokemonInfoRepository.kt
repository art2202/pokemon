package com.example.pokemon.api.repository

import com.example.pokemon.api.RestApi
import com.example.pokemon.api.model.AbilityDescriptionDataResponse
import com.example.pokemon.api.model.NamePokemonDataResponse
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.api.model.TypeListDataResponse
import com.example.pokemon.domain.Habilidade
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

    suspend fun getHabilidadesPokemon(habilidade : String): Response<AbilityDescriptionDataResponse> {

        try {
            val requisicao = restApi.getApiService().getHabilidadesPokemon("ability/" + habilidade)

            if (!requisicao.isSuccessful)
                throw Exception("Erro ao fazer requisição das habilidades do pokemon")
            else
                return requisicao
        }

        catch (e : Exception) {
            println(e.message + "info repository (habilidade)")
            throw Exception(e.message)
        }

    }

    suspend fun getEvolucao(pokemonId : Int){

        val requisicaoSpecie = restApi.getApiService().getSpecie("pokemon-species/" + pokemonId)
        val url = requisicaoSpecie.body()?.evolution_chain?.url?.removePrefix("https://pokeapi.co/api/v2/")
        val requisicaoChain = restApi.getApiService()
            .getChainEvolution(url ?: throw Exception(requisicaoSpecie.errorBody().toString()))
    }

    suspend fun getPokemonByType(tipo : String) : Response<TypeListDataResponse>{
        try {
            val requisicao = restApi.getApiService().getPokemonByType("type/" + tipo)

            if(!requisicao.isSuccessful)
                throw Exception("Erro ao fazer requisição dos pokemons pelo tipo")
            else
                return requisicao
        }
        catch (e : Exception) {
            println(e.message + "info repository (habilidade)")
            throw Exception(e.message)
        }
    }

    fun mapResponseTipo(response: Response<TypeListDataResponse>) : List<NamePokemonDataResponse>?{
        return response.body()!!.pokemon?.map {
            NamePokemonDataResponse(it.pokemon.name, it.pokemon.url?.removePrefix("https://pokeapi.co/api/v2/pokemon/")?.removeSuffix("/"))
        }
    }

    fun mapResponseHabilidade(response: Response<AbilityDescriptionDataResponse>) : Habilidade{
        return Habilidade(response.body()!!.effect_entries)
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