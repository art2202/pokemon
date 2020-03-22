package com.example.pokemon.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.repository.PokemonInfoRepository
import com.example.pokemon.common.Response
import kotlinx.coroutines.launch


class InfoPokemonViewModel(private val repository: PokemonInfoRepository) : ViewModel() {

    private val responseInfo = MutableLiveData<Response>()


    fun getInfoPokemon(nomePokemon : String){
        viewModelScope.launch {
            try{
                val requisicao = repository.getInfoPokemon(nomePokemon)
                responseInfo.postValue(Response.success(repository.mapResponse(requisicao)))
            }

            catch (t : Throwable){
                println(t.message + "  viewmodel  ")
                responseInfo.postValue(Response.error(t))
            }
        }
    }

    fun responseInfo() : MutableLiveData<Response>{
        return responseInfo
    }
}