package com.example.labapprestaurante

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labapprestaurante.databinding.ActivityRestauranteBinding

class RestauranteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestauranteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}