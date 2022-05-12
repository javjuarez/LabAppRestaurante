package com.example.labapprestaurante.apiservice

import com.example.labapprestaurante.models.RestauranteResponse
import com.example.labapprestaurante.models.UsuarioResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET
    suspend fun getUsuarios(@Url url: String): Response<UsuarioResponse>

    @GET
    suspend fun getRestaurantes(@Url url: String): Response<RestauranteResponse>

    @GET
    suspend fun getRestauranteDetalle(@Url url: String): Response<RestauranteResponse>
}