package com.udacity.shoestore.shoes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ListShoesFragmentBinding

class ListShoesFragment : Fragment() {

    private lateinit var viewModel: ListShoesViewModel
    private lateinit var binding: ListShoesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.list_shoes_fragment,
            container,
            false
        )
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListShoesViewModel::class.java)

        binding.addShoe.setOnClickListener {
            findNavController().navigate(ListShoesFragmentDirections.actionListShoesFragmentToShoesDetailsFragment())
        }
    }

}