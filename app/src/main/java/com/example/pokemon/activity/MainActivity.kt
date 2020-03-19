package com.example.pokemon.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.common.Response
import com.example.pokemon.common.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject


class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.response().observe(this, Observer { response ->  processResponse(response)})
        viewModel.getPokemon()
    }

    private fun processResponse(response: Response) {
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseSuccess(result: Any?){

        result as List<PokemonDataResponse>
        result.map { println(it.name) }
    }

    private fun responseFailure(erro : Throwable?){
        println(erro?.message)
    }
}

