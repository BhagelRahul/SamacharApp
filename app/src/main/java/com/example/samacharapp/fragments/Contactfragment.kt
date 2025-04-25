package com.example.samacharapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.samacharapp.databinding.FragmentMainBinding
import com.example.samacharapp.databinding.SportslayoutBinding

class Contactfragment : Fragment() {

    private lateinit var binding: SportslayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SportslayoutBinding.inflate(inflater, container, false)

        // Return the root view from the binding object
        return binding.root

    }

}