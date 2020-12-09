package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.databinding.ListShoesFragmentBinding
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

@BindingAdapter("android:text")
fun bindDoubleInText(tv: EditText, value: Double) {
    tv.setText(value.toString())
    // Set the cursor to the end of the text
    tv.setSelection(tv.text!!.length)
}

@InverseBindingAdapter(attribute = "android:text")
fun getDoubleFromBinding(view: TextView): Double {
    val string = view.text.toString()
    return if (string.isEmpty()) 0.0 else string.toDouble()
}