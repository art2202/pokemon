package com.example.pokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.pokemon.R
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
        constrain_info.visibility = View.INVISIBLE
        progress_info.visibility = View.VISIBLE

    }

    private fun processResponse(response: com.example.pokemon.common.Response) {
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseSuccess(result: Any?) {
//        Toast.makeText(this, "deu bom", Toast.LENGTH_SHORT).show()
        montarTela(result as Pokemon)
    }

    private fun responseFailure(erro : Throwable?){
        Toast.makeText(this, erro?.message, Toast.LENGTH_SHORT).show()


    }

    private fun montarTela(pokemon: Pokemon){

        Picasso.get().load("https://pokeres.bastionbot.org/images/pokemon/${pokemon.id}.png").into(foto_pokemon)
        
        id_pokemon.text = "#" + pokemon.id.toString()
        nome_pokemon.text = pokemon.speciesDataResponse?.name
        hp.text = "HP: " + pokemon.statsDataResponse?.find { it.stat.name == "hp" }?.base_stat.toString()
        attack.text = "ATK: " +pokemon.statsDataResponse?.find { it.stat.name == "attack" }?.base_stat.toString()
        defense.text = "DEF: " +pokemon.statsDataResponse?.find { it.stat.name == "defense" }?.base_stat.toString()
        special_attack.text = "SPECIAL ATK: " +pokemon.statsDataResponse?.find { it.stat.name == "special-attack" }?.base_stat.toString()
        special_defense.text = "SPECIAL DEF: " +pokemon.statsDataResponse?.find { it.stat.name == "special-defense" }?.base_stat.toString()
        speed.text = "SPD: " +pokemon.statsDataResponse?.find { it.stat.name == "speed" }?.base_stat.toString()

        constrain_info.visibility = View.VISIBLE
        progress_info.visibility = View.GONE

    }

}
