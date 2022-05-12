package com.example.labapprestaurante.ui.registro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import com.example.labapprestaurante.databinding.FragmentRegistroBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class RegistroFragment : Fragment() {

    private var _binding: FragmentRegistroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        removeErrorsTextFields()
        binding.buttonRegistrar.setOnClickListener {
            showRegistrar()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showRegistrar() {
        if (checkCamposCorrectos())
            Toast.makeText(context, "Registro innecesario, campos correctos.", Toast.LENGTH_SHORT)
                .show()
        else
            Toast.makeText(context, "Campos incorrectos.", Toast.LENGTH_SHORT)
                .show()
    }

    private fun removeErrorsTextFields() {
        for (i in 0..binding.root.childCount) {
            val item = binding.root.getChildAt(i)
            if (item is TextInputLayout) {
                val frameLayout = item.getChildAt(0) as FrameLayout
                for (j in 0..frameLayout.childCount) {
                    val frameChild = frameLayout.getChildAt(j)
                    if (frameChild is TextInputEditText) {
                        frameChild.doOnTextChanged { _, _, _, _ ->
                            item.isErrorEnabled = false
                            item.error = null
                        }
                        break
                    }
                }
            }
        }
    }

    private fun checkCamposCorrectos(): Boolean {
        var correctos = true
        for (i in 0..binding.root.childCount) {
            val item = binding.root.getChildAt(i)
            if (item is TextInputLayout) {
                val frameLayout = item.getChildAt(0) as FrameLayout
                for (j in 0..frameLayout.childCount) {
                    val frameChild = frameLayout.getChildAt(j)
                    if (frameChild is TextInputEditText) {
                        val texto = frameChild.text.toString()
                        if (texto.isEmpty()) {
                            item.isErrorEnabled = true
                            item.error = "El campo no puede estar vacío"
                            correctos = false
                        }
                        break
                    }
                }
            }
        }
        return correctos
    }
}