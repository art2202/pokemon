package com.example.pokemon.di

import com.example.pokemon.ui.viewModel.MainViewModel
import com.example.pokemon.api.RestApi
import com.example.pokemon.api.repository.PokemonInfoRepository
import com.example.pokemon.api.repository.PokemonRepository
import com.example.pokemon.ui.viewModel.InfoPokemonViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val modules = module{
    single{ RestApi() }
}

val repositoryModulo = module {
    single {PokemonRepository(get())}
    single {PokemonInfoRepository(get())}
}

val viewModelModulo = module{
    viewModel { MainViewModel(get()) }
    viewModel { InfoPokemonViewModel(get()) }
}
