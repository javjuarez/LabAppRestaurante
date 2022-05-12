package com.example.labapprestaurante

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout.LayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labapprestaurante.adapters.RestauranteFotosAdapter
import com.example.labapprestaurante.apiservice.APIResponses
import com.example.labapprestaurante.databinding.ActivityRestauranteDetailBinding
import com.example.labapprestaurante.decorators.MarginItemDecorator
import com.example.labapprestaurante.models.Restaurante
import com.example.labapprestaurante.models.RestauranteResponse
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestauranteDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRestauranteDetailBinding
    private lateinit var extras: Bundle
    private lateinit var adapter: RestauranteFotosAdapter
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRestauranteDetailBinding.inflate(layoutInflater)
        extras = intent.extras!!
        id = extras.getInt("id", 0)
        setContentView(binding.root)

        Toast.makeText(this, "Id recicbido: $id", Toast.LENGTH_SHORT).show()
        getRestauranteDetalle(id)
    }

    private fun prepareUI(restaurante: Restaurante) {
        adapter = RestauranteFotosAdapter(restaurante.foto)
        val emptyStarsCount = 5 - restaurante.calificacion.toInt()
        val layoutStarIcon = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        with(binding) {
            Picasso.get().load(restaurante.foto[0]).into(imageViewRestauranteMain)
            textViewTituloRestaurante.text = restaurante.nombre
            textViewDireccionRestaurante.text = restaurante.direccion
            textViewReviewRestaurante.text = restaurante.resenia
            textViewFundacionCosto.text =
                "Fundado en ${restaurante.fundacion} --- Costo promedio \$${restaurante.costoPromedio}"

            recyclerViewFotosRestaurantes.layoutManager = LinearLayoutManager(
                this@RestauranteDetailActivity,
                LinearLayoutManager.HORIZONTAL,
                false
            )
            recyclerViewFotosRestaurantes.addItemDecoration(MarginItemDecorator(16))
            recyclerViewFotosRestaurantes.adapter = adapter
            for (i in 1..restaurante.calificacion.toInt()) {
                val starFullIcon = ImageView(this@RestauranteDetailActivity)
                starFullIcon.layoutParams = layoutStarIcon
                starFullIcon.setImageResource(R.drawable.ic_star)
                linearLayoutCalif.addView(starFullIcon)
            }
            for (i in 1..emptyStarsCount) {
                val starFullIcon = ImageView(this@RestauranteDetailActivity)
                starFullIcon.layoutParams = layoutStarIcon
                starFullIcon.setImageResource(R.drawable.ic_star_border)
                linearLayoutCalif.addView(starFullIcon)
            }
        }
    }

    private fun getRestauranteDetalle(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = APIResponses.getRestauranteDetailResponse(id)
            val restauranteResponse: RestauranteResponse? = call.body()
            runOnUiThread {
                if (call.isSuccessful)
                    if (restauranteResponse != null) {
                        prepareUI(restauranteResponse.restaurantes[0])
                    } else
                        Toast.makeText(
                            this@RestauranteDetailActivity,
                            "Ocurrió un error",
                            Toast.LENGTH_SHORT
                        ).show()
            }
        }
    }
}