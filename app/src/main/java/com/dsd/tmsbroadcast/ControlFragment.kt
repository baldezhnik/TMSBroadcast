package com.dsd.tmsbroadcast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.dsd.tmsbroadcast.databinding.FragmentControlBinding

class ControlFragment : Fragment() {

    lateinit var binding: FragmentControlBinding

    private val viewModel by activityViewModels<CatsViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentControlBinding.inflate(inflater)

        binding.btnAddEat.setOnClickListener {
            viewModel.addPlusOne("eat")
        }

        binding.btnAddDrink.setOnClickListener {
            viewModel.addPlusOne("drink")
        }

        binding.btnAddPleasure.setOnClickListener {
            viewModel.addPlusOne("care")
        }

        viewModel.cat.observe(this.viewLifecycleOwner, Observer {
            binding.pbEat.progress = it.hunger
            binding.pbDrink.progress = it.thirst
            binding.pbPleasure.progress = it.caress
        })

        return binding.root
    }
}