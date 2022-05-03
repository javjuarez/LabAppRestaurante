package com.example.labapprestaurante.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.labapprestaurante.adapters.RestauranteAdapter
import com.example.labapprestaurante.apiservice.APIResponses
import com.example.labapprestaurante.databinding.FragmentGalleryBinding
import com.example.labapprestaurante.models.Restaurante
import com.example.labapprestaurante.models.RestauranteResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RestaurantesFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var adapter: RestauranteAdapter
    private var restaurantes = mutableListOf<Restaurante>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        getRestaurantes()

        adapter = RestauranteAdapter(restaurantes) {

        }
        binding.recyclerViewRestaurantes.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewRestaurantes.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRestaurantes() {
        lifecycleScope.launch(Dispatchers.IO) {
            val call = APIResponses.getRestaurantesResponse()
            val restauranteResponse: RestauranteResponse? = call.body()
            viewLifecycleOwner.lifecycleScope.launch {
                if (call.isSuccessful) {
                    if (restauranteResponse != null) {
                        restaurantes.clear()
                        restaurantes.addAll(restauranteResponse.restaurantes)
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
//        CoroutineScope(Dispatchers.IO).launch {
//            val call = APIResponses.getUsuariosResponse()
//            val usuarioResponse: UsuarioResponse? = call.body()
//            runOnUiThread {
//                if (call.isSuccessful) {
//                    if (usuarioResponse != null) {
//                        Toast.makeText(
//                            context,
//                            "Usuario 1 es: ${usuarioResponse.usuarios[0].nombre}",
//                            Toast.LENGTH_LONG
//                        ).show()
//                    }
//                }
//            }
//        }
    }
}