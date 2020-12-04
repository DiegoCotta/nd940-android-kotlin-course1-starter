package com.udacity.shoestore.shoes

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.udacity.shoestore.R

class ListShoesFragment : Fragment() {

    companion object {
        fun newInstance() = ListShoesFragment()
    }

    private lateinit var viewModel: ListShoesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.list_shoes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ListShoesViewModel::class.java)
        // TODO: Use the ViewModel
    }

}