package com.example.labapprestaurante.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.labapprestaurante.databinding.ItemRecyclerDetalleRestauranteFotosBinding
import com.squareup.picasso.Picasso

class RestauranteFotosAdapter(private val fotosRestaurantes: List<String>) :
    RecyclerView.Adapter<RestauranteFotosAdapter.RestauranteFotosViewHolder>() {

    private lateinit var binding: ItemRecyclerDetalleRestauranteFotosBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestauranteFotosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        binding = ItemRecyclerDetalleRestauranteFotosBinding.inflate(layoutInflater)
        return RestauranteFotosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestauranteFotosViewHolder, position: Int) {
        holder.bind(fotosRestaurantes[position])
    }

    override fun getItemCount(): Int {
        return fotosRestaurantes.size
    }

    class RestauranteFotosViewHolder(private val binding: ItemRecyclerDetalleRestauranteFotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foto: String) {
            Picasso.get().load(foto).into(binding.imageViewFoto)
        }
    }

}