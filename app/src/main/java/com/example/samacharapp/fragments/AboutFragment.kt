package com.example.samacharapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.BinderThread
import com.example.samacharapp.R
import com.example.samacharapp.databinding.FragmentAboutBinding


class AboutFragment : Fragment() {

    lateinit var binding: FragmentAboutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

       binding= FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    }
