package com.example.examenpractico03

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("v1/IngenieriaEnSistemas")
    fun obtenerRecursos(): Call<List<Recurso>>

    @GET("v1/IngenieriaEnSistemas/{id}")
    fun obtenerRecursoPorId(@Path("id") id: Int): Call<Recurso>
}