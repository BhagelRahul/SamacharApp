package com.example.samacharapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.samacharapp.R
import com.example.samacharapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

     lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding=FragmentMainBinding.inflate(inflater, container, false)
      return  binding.root
    }


    }
