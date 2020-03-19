package com.example.pokemon.di

import com.example.pokemon.activity.MainViewModel
import com.example.pokemon.api.RestApi
import com.example.pokemon.api.repository.PokemonRepository
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val modules = module{
    single{ RestApi() }
}

val repositoryModulo = module {
    single {PokemonRepository(get())}
}

val viewModelModulo = module{
    viewModel { MainViewModel(get() ) }
}
