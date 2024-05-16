package com.example.examenpractico03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.examenpractico03.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null

    private lateinit var api: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Creaa una instancia de Retrofit con el cliente OkHttpClient
        val retrofit = Retrofit.Builder()
            .baseUrl("https://66459ebeb8925626f8926bd8.mockapi.io/api/v1/IngenieriaEnSistemas")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Crea una instancia del servicio que utiliza la aoutenticacion HTTP basica
        api = retrofit.create(ApiService::class.java)
    }
}