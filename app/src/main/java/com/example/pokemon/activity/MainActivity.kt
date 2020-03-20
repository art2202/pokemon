package com.example.pokemon.activity

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.api.model.PokemonListResponse
import com.example.pokemon.common.Response
import com.example.pokemon.common.Status
import kotlinx.android.synthetic.main.activity_main.*
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
        inicializaRecyclerView(result)

    }

    private fun responseFailure(erro : Throwable?){
        println(erro?.message)
    }

    private fun inicializaRecyclerView(pokemons : List<PokemonDataResponse>){
        recycler_view_pokemons.layoutManager = LinearLayoutManager(this)
        recycler_view_pokemons.setHasFixedSize(true)
        recycler_view_pokemons.adapter = PokemonAdapter(pokemons, this)
        recycler_view_pokemons.isNestedScrollingEnabled = true
    }
}

