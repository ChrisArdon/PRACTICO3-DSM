package com.example.examenpractico03

import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("materia/recurso")
    fun obtenerRecursos(): Call<List<recurso>>

    @GET("materia/recurso/{id}")
    fun obtenerRecursoPorId(@Path("id") id: Int): Call<recurso>
}