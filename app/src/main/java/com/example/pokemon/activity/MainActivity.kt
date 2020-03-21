package com.example.pokemon.activity

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.example.pokemon.R
import com.example.pokemon.adapter.PokemonAdapter
import com.example.pokemon.api.model.PokemonDataResponse
import com.example.pokemon.common.Response
import com.example.pokemon.common.Status
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by viewModel()
    private var i = 1
    var isLoading = false
    private val visibleThreshold = 5
    private var firstVisibleItem = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0
    private var previousTotal = 0
    val layoutManager = LinearLayoutManager(this)
    private lateinit var adapter : PokemonAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.response().observe(this, Observer { response ->  processResponse(response)})
        viewModel.responsePaginacao().observe(this, Observer { maisResponse -> processResponseScroll(maisResponse)})
        progress_circular.visibility = View.VISIBLE
        viewModel.getPokemon()

    }

    private fun processResponseScroll(response: Response){
        when (response.status) {
            Status.SUCCESS -> responseSuccessScroll(response.data)
            Status.ERROR -> responseFailure(response.error)
        }

    }

    private fun responseSuccessScroll(result: Any?){
        result as ArrayList<PokemonDataResponse>
        adapter.notifyDataSetChanged()
        progress_circular.visibility = View.GONE
//        println(result.size)


    }
    private fun processResponse(response: Response) {
        when (response.status) {
            Status.SUCCESS -> responseSuccess(response.data)
            Status.ERROR -> responseFailure(response.error)
        }
    }

    private fun responseSuccess(result: Any?){

        result as ArrayList<PokemonDataResponse>
        viewModel.setListaPokemon(result)
        inicializaRecyclerView(result)
        progress_circular.visibility = View.GONE
        paginacao()

    }

    private fun responseFailure(erro : Throwable?){
        println(erro?.message)
    }

    private fun inicializaRecyclerView(pokemons : ArrayList<PokemonDataResponse>){

        adapter = PokemonAdapter(pokemons, this)
        recycler_view_pokemons.layoutManager = layoutManager
        recycler_view_pokemons.setHasFixedSize(true)
        recycler_view_pokemons.adapter = adapter
        recycler_view_pokemons.isNestedScrollingEnabled = true

    }

    fun paginacao(){
        recycler_view_pokemons.addOnScrollListener( object : OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = recyclerView.childCount
                totalItemCount = layoutManager.itemCount
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if(dy > 0){
                    if(isLoading)
                        if (totalItemCount>previousTotal){
                            isLoading = false
                            previousTotal = totalItemCount
                        }
                    if(!isLoading&&(totalItemCount-visibleItemCount)<=(firstVisibleItem+visibleThreshold)){
                        if(i < 10){

                            viewModel.getPokemon(i)
                            progress_circular.visibility = View.VISIBLE
                            i++
                        }
                        isLoading = true
                    }
                }
            }
        } )
    }

}

