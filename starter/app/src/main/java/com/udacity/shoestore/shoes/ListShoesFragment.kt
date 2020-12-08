package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.databinding.ListShoesFragmentBinding
import com.udacity.shoestore.login.LoginViewModel
import com.udacity.shoestore.models.Shoe

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

        viewModel = ViewModelProvider(requireActivity()).get(ListShoesViewModel::class.java)

        setupObservables()

        return binding.root
    }

    private fun setupObservables() {
        viewModel.shoesList.observe(this, Observer { list ->
            binding.listShoes.removeAllViews()
            list.forEach { shoe ->
                val inflater = LayoutInflater.from(binding.listShoes.context)
                val binding: ItemShoeBinding =
                    ItemShoeBinding.inflate(inflater, binding.listShoes, true)
                binding.shoe = shoe
            }

        })
    }

    override fun onResume() {
        super.onResume()
        val args = ListShoesFragmentArgs.fromBundle(requireArguments())
        if (args.shoeName != null && args.company != null && args.description != null) {
            viewModel.addOnList(
                Shoe(
                    args.shoeName,
                    args.size.toDouble(),
                    args.company,
                    args.description
                )
            )
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.addShoe.setOnClickListener {
            findNavController().navigate(ListShoesFragmentDirections.actionListShoesFragmentToShoesDetailsFragment())
        }
    }

}