package com.udacity.shoestore.instruction

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.InstructionFragmentBinding
import com.udacity.shoestore.welcome.WelcomeFragmentDirections

class InstructionFragment : Fragment() {


    lateinit var binding: InstructionFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.instruction_fragment,
            container,
            false
        )

        binding.nextButton.setOnClickListener {
            findNavController().navigate(
                InstructionFragmentDirections.actionInstructionFragmentToListShoesFragment(
                    null,
                    null,
                    null,
                    0f
                )
            )
        }

        return binding.root
    }

}