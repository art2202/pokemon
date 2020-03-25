package com.example.pokemon.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemon.api.repository.PokemonInfoRepository
import com.example.pokemon.common.Response
import kotlinx.coroutines.launch


class InfoPokemonViewModel(private val repository: PokemonInfoRepository) : ViewModel() {

    private val responseInfo = MutableLiveData<Response>()
    private val responseHabilidades = MutableLiveData<Response>()
    private val responsePokemonsTypes = MutableLiveData<Response>()


    fun getInfoPokemon(nomePokemon : String){
        viewModelScope.launch {
            try{
                val requisicao = repository.getInfoPokemon(nomePokemon)
                responseInfo.postValue(Response.success(repository.mapResponse(requisicao)))
            }

            catch (t : Throwable){
                println(t.message + "  getInfoPokemon viewmodel  ")
                responseInfo.postValue(Response.error(t))
            }
        }
    }

    fun getHabilidades(habilidade : String){
        viewModelScope.launch {
            try {
                val requisicao = repository.getHabilidadesPokemon(habilidade)
                responseHabilidades.postValue(Response.success(repository.mapResponseHabilidade(requisicao)))
            }
            catch (t : Throwable){
                println(t.message + "  getHabilidades viewmodel  ")
                responseHabilidades.postValue(Response.error(t))
            }
        }
    }

    fun getEvolucao(idPokemon : Int){

        viewModelScope.launch {
            try {
                repository.getEvolucao(idPokemon)
            }
            catch (t : Throwable){
                println(t.message + "  getEvolucao viewmodel  ")
            }
        }
    }

    fun getPokemonByType(tipo : String){
        viewModelScope.launch {
            try {
                val requisicao = repository.getPokemonByType(tipo)
                responsePokemonsTypes.postValue(Response.success(repository.mapResponseTipo(requisicao)))
            }
            catch (t : Throwable){
                println(t.message + "  getHabilidades viewmodel  ")
                responsePokemonsTypes.postValue(Response.error(t))
            }
        }
    }

    fun responseInfo() : MutableLiveData<Response>{
        return responseInfo
    }

    fun responseHabilidades() : MutableLiveData<Response>{
        return responseHabilidades
    }

    fun responsePokemonsTypes() : MutableLiveData<Response>{
        return responsePokemonsTypes
    }



}