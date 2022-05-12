package com.example.labapprestaurante.apiservice

import com.example.labapprestaurante.models.RestauranteResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APIResponses {

    companion object {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://demo4662239.mockable.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        suspend fun getRestaurantesResponse(): Response<RestauranteResponse> {
            return retrofit.create(APIService::class.java).getRestaurantes("restaurantes")
        }

        suspend fun getRestauranteDetailResponse(id: Int): Response<RestauranteResponse> {
            return retrofit.create(APIService::class.java).getRestauranteDetalle("restaurantes/$id")
        }
    }
}