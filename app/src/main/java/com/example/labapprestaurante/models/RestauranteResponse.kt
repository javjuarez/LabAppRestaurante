package com.example.labapprestaurante.models

data class RestauranteResponse(
    val status: String,
    val restaurantes: List<Restaurante>
)
