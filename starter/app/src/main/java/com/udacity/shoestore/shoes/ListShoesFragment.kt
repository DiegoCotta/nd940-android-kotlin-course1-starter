package com.udacity.shoestore.shoes

import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.InverseBindingAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.ItemShoeBinding
import com.udacity.shoestore.databinding.ListShoesFragmentBinding
import com.udacity.shoestore.models.Shoe


class ListShoesFragment : Fragment() {

    private lateinit var binding: ListShoesFragmentBinding
    private val viewModel: ListShoesViewModel by activityViewModels()

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

        setupObservables()
        setHasOptionsMenu(true)

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.addShoe.setOnClickListener {
            findNavController().navigate(ListShoesFragmentDirections.actionListShoesFragmentToShoesDetailsFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.app_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.logout -> {
                findNavController().navigateUp()
                true
            }
            else -> super.onOptionsItemSelected(item)
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