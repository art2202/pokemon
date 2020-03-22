package com.example.pokemon.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.R
import com.example.pokemon.adapter.MovesAdapter
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.adapter.TipoAdapter
import com.example.pokemon.api.model.AbilitiesDataResponse
import com.example.pokemon.api.model.NamePokemonDataResponse
import com.example.pokemon.api.model.TypeDataResponse
import com.example.pokemon.common.Response
import com.example.pokemon.common.Status
import com.example.pokemon.domain.Habilidade
import com.example.pokemon.domain.Pokemon
import com.example.pokemon.ui.clicks.ClickTipo
import com.example.pokemon.ui.clicks.EventoClick
import com.example.pokemon.ui.clicks.MostrarHabilidades
import com.example.pokemon.ui.viewModel.InfoPokemonViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_info_pokemon.*
import kotlinx.android.synthetic.main.dialog_tipo.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class InfoPokemonActivity : AppCompatActivity() ,
    MostrarHabilidades, ClickTipo, EventoClick {

    private val viewModel : InfoPokemonViewModel by viewModel()
    val layoutManager = GridLayoutManager(this, 2)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_pokemon)
        viewModel.responsePokemonsTypes().observe(this, Observer { responseType -> processResponseTipo(responseType) })
        viewModel.responseInfo().observe(this, Observer { response ->  processResponse(response)})
        viewModel.responseHabilidades().observe(this, Observer { responseHabilidade -> processResponseHabilidade(responseHabilidade) })

        viewModel.getInfoPokemon(intent.getStringExtra("nome") ?: throw Exception("Nome invalido"))
        constrain_info.visibility = View.INVISIBLE
        progress_info.visibility = View.VISIBLE

    }

    private fun processResponseTipo(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseTipoSucess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseTipoSucess(result: Any?){
        result as ArrayList<NamePokemonDataResponse>

        val dialog = AlertDialog.Builder(this)
        val view = layoutInflater.inflate(R.layout.dialog_tipo, null)
        view.recycler_tipo_pokemons.layoutManager = GridLayoutManager(this, 2)
        view.recycler_tipo_pokemons.adapter = PokemonAdapter(result, this, this)
        view.recycler_tipo_pokemons.isNestedScrollingEnabled = true

        dialog.setView(view).setPositiveButton("sair", null).create().show()

    }

    private fun processResponseHabilidade(response: Response){

        when (response.status) {
            Status.SUCCESS -> responseHabilidadeSucess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseHabilidadeSucess(result : Any?){
        result as Habilidade

        AlertDialog.Builder(this).setTitle("Description").setMessage(result.efeitos[0].effect).setPositiveButton("ok",  { _, _ ->}).create().show()
    }

    private fun processResponse(response: Response) {
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseSuccess(result: Any?) {
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

        recycler_tipo.adapter = TipoAdapter(this, pokemon.typesDataResponse, this)
        recycler_tipo.layoutManager = GridLayoutManager(this, 3)
        recycler_tipo.isNestedScrollingEnabled = false

        recycler_moves.layoutManager = layoutManager
        recycler_moves.isNestedScrollingEnabled = false
        recycler_moves.adapter = MovesAdapter(this, pokemon.habilidades, this)


    }

    override fun listarTipo(item: AbilitiesDataResponse) {
//        Toast.makeText(this, "Aguarde", Toast.LENGTH_SHORT).show()
        viewModel.getHabilidades(item.ability?.name!!)
    }

    override fun mostrarTipo(item: TypeDataResponse) {
        viewModel.getPokemonByType(item.name!!)

    }

    override fun clickMostrarInfo(item: NamePokemonDataResponse) {}

}
