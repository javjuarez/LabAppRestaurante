package com.example.labapprestaurante.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.labapprestaurante.databinding.ItemRecyclerRestauranteBinding
import com.example.labapprestaurante.models.Restaurante
import com.squareup.picasso.Picasso

class RestauranteAdapter(private val restaurantes: List<Restaurante>, val clickListener: (Restaurante) -> Unit) :
    RecyclerView.Adapter<RestauranteAdapter.RestauranteViewHolder>() {

    private lateinit var binding: ItemRecyclerRestauranteBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteViewHolder {
        val layoutInflater =LayoutInflater.from(parent.context)
        binding = ItemRecyclerRestauranteBinding.inflate(layoutInflater)
        return RestauranteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestauranteViewHolder, position: Int) {
        holder.bind(restaurantes[position])

        holder.itemView.setOnClickListener {
            clickListener(restaurantes[position])
        }
    }

    override fun getItemCount(): Int {
        return restaurantes.size
    }

    class RestauranteViewHolder(private val binding: ItemRecyclerRestauranteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurante: Restaurante) {
            with(binding) {
                textViewNombreRestaurante.text = restaurante.nombre
                textViewDireccion.text = restaurante.direccion
                textViewResenia.text = restaurante.resenia
                Picasso.get().load(restaurante.foto[0]).into(binding.imageViewRestaurante)
            }
        }
    }

}