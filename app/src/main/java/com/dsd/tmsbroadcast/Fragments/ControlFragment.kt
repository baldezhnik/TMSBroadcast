package com.dsd.tmsbroadcast.Fragments

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.dsd.tmsbroadcast.Classes.Cat
import com.dsd.tmsbroadcast.Model.CatsViewModel
import com.dsd.tmsbroadcast.databinding.FragmentControlBinding

class ControlFragment : Fragment() {

    lateinit var binding: FragmentControlBinding

    private val viewModel by activityViewModels<CatsViewModel>()
    val intent = Intent("com.dsd.tmsbroadcast.android.action.broadcast")
    var bundle = Bundle(3)
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
            binding.tvActionPleasure.text= binding.pbPleasure.progress.toString()
            binding.tvActionDrink.text= binding.pbDrink.progress.toString()
            binding.tvActionEat.text= binding.pbEat.progress.toString()
            bundle.putInt("value", it.thirst)
            bundle.putInt("value", it.caress)
            bundle.putInt("value", it.hunger)
            intent.putExtras(bundle)
            activity?.sendBroadcast(intent)
        })

        return binding.root
    }


}