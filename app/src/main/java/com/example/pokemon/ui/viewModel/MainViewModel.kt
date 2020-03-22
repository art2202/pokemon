package com.example.pokemon.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.repository.PokemonRepository
import com.example.pokemon.common.Response
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.model.NamePokemonDataResponse
import kotlinx.coroutines.launch


class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val response = MutableLiveData<Response>()
    private val responsePaginacao = MutableLiveData<Response>()

    private var listaPokemons = arrayListOf<NamePokemonDataResponse>()

    fun getPokemon(){
        viewModelScope.launch {
            try {
                val requisicao = pokemonRepository.getListaPokemon(0)

                listaPokemons.addAll(pokemonRepository.mapResponse(requisicao) ?: listOf())
                println("primeira vez " + listaPokemons.size)

                response.postValue(Response.success(listaPokemons))
            }
            catch (t : Throwable){
                println(t.message)
                response.postValue(Response.error(t))

            }
        }
    }

    fun getPokemon(valor : Int) {
        var offset = valor*100

        viewModelScope.launch {
            try {
                println(listaPokemons.size)
                val requisicao = pokemonRepository.getListaPokemon(offset)
//                println("antes de add " + lista?.size)
                listaPokemons.addAll(pokemonRepository.mapResponse(requisicao) ?: listOf())
                println("depois de add "+ listaPokemons.size)
                responsePaginacao.postValue(Response.success(listaPokemons))

            }
            catch (t : Throwable){
                println(t.message)
                response.postValue(Response.error(t))

            }
        }
    }

    fun setListaPokemon(lista : ArrayList<NamePokemonDataResponse>){
        listaPokemons = lista
    }



    fun response() : MutableLiveData<Response> {
        return response
    }

    fun responsePaginacao() : MutableLiveData<Response>{
        return responsePaginacao
    }

}