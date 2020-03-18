package com.example.pokemon.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemon.R
import com.example.pokemon.api.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
            requisicao()
        }
    }

    fun requisicao(){


        val call = RetrofitInitializer().apiService().pokemons()
        call.enqueue(object : Callback<List<Any>>{
            override fun onResponse(call: Call<List<Any>>, response: Response<List<Any>>) {
                println(response.body())
            }
            override fun onFailure(call: Call<List<Any>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}

