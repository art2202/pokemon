package com.example.pokemon

import android.app.Application
import com.example.pokemon.di.modules
import com.example.pokemon.di.repositoryModulo
import com.example.pokemon.di.viewModelModulo
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

val myModule = listOf(modules, viewModelModulo, repositoryModulo)
class MyApplication : Application() {


    companion object{
        lateinit var instance : MyApplication
    }

    init{
        instance = this
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MyApplication)
            modules(myModule)
        }
    }
}