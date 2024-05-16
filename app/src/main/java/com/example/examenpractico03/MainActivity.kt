package com.example.examenpractico03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Credentials
import okhttp3.OkHttpClient
import com.example.examenpractico03.databinding.ActivityMainBinding
import retrofit2.Retrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null
    private lateinit var recyclerView: RecyclerView
    private lateinit var api: ApiService
    private lateinit var adapter: RecursoAdapter

    //Obtener las credenciales de autenticacion
    val auth_username = "admin"
    val auth_password = "admin123"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        //Creaa una instancia de Retrofit con el cliente OkHttpClient
        val retrofit = Retrofit.Builder()
            .baseUrl("https://66459ebeb8925626f8926bd8.mockapi.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        //Crea una instancia del servicio que utiliza la aoutenticacion HTTP basica
        api = retrofit.create(ApiService::class.java)

        cargarDatos(api)
    }

    override fun onResume() {
        super.onResume()
        cargarDatos(api)
    }

    private fun cargarDatos(api: ApiService){
        val call = api.obtenerRecursos()
        call.enqueue(object : Callback<List<Recurso>>{
            override fun onResponse(call: Call<List<Recurso>>, response: Response<List<Recurso>>) {
                if(response.isSuccessful){
                    val recursos = response.body()
                    if(recursos != null){
                        adapter = RecursoAdapter(recursos)
                        recyclerView.adapter = adapter

                    }
                } else {
                    val error = response.errorBody()?.string()
                    Log.e("API", "Error al obtener los recursos: $error")
                    Toast.makeText(this@MainActivity, "Error al obtener los recursos 1", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<Recurso>>, t: Throwable) {
                Log.e("API", "Error al obtener los recursos: ${t.message}")
                Toast.makeText(this@MainActivity, "Error al obtener los recursos 2", Toast.LENGTH_SHORT).show()
            }
        })
    }
}