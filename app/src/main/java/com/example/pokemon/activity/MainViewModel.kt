package com.example.pokemon.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemon.api.repository.PokemonRepository
import com.example.pokemon.common.Response
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class MainViewModel(private val pokemonRepository: PokemonRepository) : ViewModel() {

    private val response = MutableLiveData<Response>()


    fun getPokemon(){
        viewModelScope.launch {
            try {

                response.postValue(Response.success(pokemonRepository.getListaPokemon()))
            }
            catch (t : Throwable){
                println(t.message)
                response.postValue(Response.error(t))

            }
        }
    }




    fun response() : MutableLiveData<Response> {
        return response
    }

}