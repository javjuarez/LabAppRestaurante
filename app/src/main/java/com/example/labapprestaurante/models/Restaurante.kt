package com.example.labapprestaurante.models

import com.google.gson.annotations.SerializedName

data class Restaurante(
    val id: Number,
    val nombre: String,
    val foto: List<String>,
    val direccion: String,
    @SerializedName("reseña") val resenia: String,
    val calificacion: Number,
    val fundacion: Number,
    val costoPromedio: Number
)
