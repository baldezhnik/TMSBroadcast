package com.dsd.tmsbroadcast

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dsd.tmsbroadcast.databinding.FragmentCatImageBinding


class CatImageFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentCatImageBinding.inflate(inflater)
        return binding.root
    }
}