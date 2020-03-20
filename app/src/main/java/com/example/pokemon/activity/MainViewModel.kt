package com.example.pokemon.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.repository.PokemonRepository
import com.example.pokemon.common.Response
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.model.PokemonDataResponse
import kotlinx.coroutines.launch


class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val response = MutableLiveData<Response>()

    private var listaPokemons = arrayListOf<PokemonDataResponse>()

    fun getPokemon(){
        viewModelScope.launch {
            try {
                val lista = pokemonRepository.getListaPokemon(0)
                listaPokemons.addAll(lista ?: listOf())
                response.postValue(Response.success(listaPokemons))
            }
            catch (t : Throwable){
                println(t.message)
                response.postValue(Response.error(t))

            }
        }
    }

    fun getPokemon(valor : Int) : List<PokemonDataResponse>{
        var offset = valor*100

        viewModelScope.launch {
            try {
                val lista = pokemonRepository.getListaPokemon(offset)
                listaPokemons.addAll(lista ?: listOf())

            }
            catch (t : Throwable){
                println(t.message)
                response.postValue(Response.error(t))

            }
        }
        return listaPokemons
    }




    fun response() : MutableLiveData<Response> {
        return response
    }

}