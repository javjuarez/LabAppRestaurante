package com.example.labapprestaurante.models

data class Usuario(
    val id: Number,
    val nombre: String,
    val username: String,
    val correo: String,
    val sexo: String,
    val habilitado: Boolean
)