package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ShoesDetailsFragmentBinding
import com.udacity.shoestore.login.LoginViewModel
import com.udacity.shoestore.models.Shoe

class ShoesDetailsFragment : Fragment() {

    private lateinit var binding: ShoesDetailsFragmentBinding

    private val shoe = Shoe("", 0.0, "", "")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.shoes_details_fragment,
            container,
            false
        )

        setupClickButtons()

        binding.shoe = shoe
        binding.lifecycleOwner = this

        return binding.root
    }

    private fun setupClickButtons() {
        binding.cancelButton.setOnClickListener {
            findNavController().navigate(
                ShoesDetailsFragmentDirections.actionShoesDetailsFragmentToListShoesFragment(
                    null,
                    null,
                    null,
                    0f
                )
            )
        }

        binding.saveButton.setOnClickListener {
            if (shoe.name.isBlank() && shoe.company.isBlank()
                && shoe.size == 0.0 && shoe.description.isBlank()
            ) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.inputs_error),
                    Toast.LENGTH_SHORT
                )
                    .show()
            } else {
                findNavController().navigate(
                    ShoesDetailsFragmentDirections.actionShoesDetailsFragmentToListShoesFragment(
                        shoe.name,
                        shoe.company,
                        shoe.description,
                        shoe.size.toFloat()
                    )
                )
            }
        }
    }
}