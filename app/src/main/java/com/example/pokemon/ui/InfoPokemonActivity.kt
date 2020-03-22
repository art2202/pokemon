package com.example.pokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.pokemon.R
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.common.Status
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.ui.viewModel.InfoPokemonViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info_pokemon.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoPokemonActivity : AppCompatActivity() {

    private val viewModel : InfoPokemonViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_pokemon)
        viewModel.responseInfo().observe(this, Observer { response ->  processResponse(response)})
        viewModel.getInfoPokemon(intent.getStringExtra("nome") ?: throw Exception("Nome invalido"))

    }

    private fun processResponse(response: com.example.pokemon.common.Response) {
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseSuccess(result: Any?) {
        Toast.makeText(this, "deu bom", Toast.LENGTH_SHORT).show()

        result as Pokemon

        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${result.id}.png").into(foto_pokemon)
        id_pokemon.text = result.id.toString()


    }

    private fun responseFailure(erro : Throwable?){
        Toast.makeText(this, erro?.message, Toast.LENGTH_SHORT).show()


    }

}
