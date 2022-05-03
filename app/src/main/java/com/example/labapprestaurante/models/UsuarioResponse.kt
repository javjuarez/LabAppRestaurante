package com.example.labapprestaurante.models

data class UsuarioResponse(
    val status: String,
    val usuarios: List<Usuario>
)