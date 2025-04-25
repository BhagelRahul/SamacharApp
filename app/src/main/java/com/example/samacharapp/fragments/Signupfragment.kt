package com.example.samacharapp.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.drm.allinone.utils.goToActivity
import com.drm.allinone.utils.showToastLong
import com.example.samacharapp.HomeActivity
import com.example.samacharapp.databinding.SignupFragmentBinding
import com.google.firebase.auth.FirebaseAuth
import kotlin.toString

class Signupfragment : Fragment() {
    lateinit var binding: SignupFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SignupFragmentBinding.inflate(inflater, container, false)

        setListeners()
        return binding.root
    }

    private fun setListeners() = binding.apply {
        signUp.setOnClickListener {
            validate()
        }

        loginText.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(Loginfragment())
        }
    }

    private fun validate() = binding.apply {
        if (usermail.text.toString().isEmpty()) {
            usermail.error = "Email is Empty"
        } else if (!Patterns.EMAIL_ADDRESS.matcher(usermail.text.toString()).matches()) {
            usermail.error = "Email is not valid"
        } else if (passWord.text.toString().isEmpty()) {
            passWord.error = "Password is Empty"
        } else if (passWord.text.toString().length != 8) {
            passWord.error = "Password must be of 8 character"
        } else if (confirmPassword.text.toString().isEmpty()) {
            confirmPassword.error = "Password is Empty"
        } else if (confirmPassword.text.toString().length != 8) {
            confirmPassword.error = "Password must be of 8 character"
        } else if (confirmPassword.text.toString() != passWord.text.toString()) {
            confirmPassword.error = "Password do not match"
            passWord.error = "Password do not match"
        } else {
            val username = usermail.text.toString()
            val password = passWord.text.toString()

            usermail.text.clear()
            passWord.text.clear()
            confirmPassword.text.clear()

            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(username, password)
                .addOnCompleteListener { result ->
                    if (result.isSuccessful) {
                        requireActivity().showToastLong("User Registered Successfully")
                        requireActivity().goToActivity(HomeActivity::class.java)
                    } else {
                        requireActivity().showToastLong("Something Went Wrong")
                    }
                }.addOnFailureListener { exception ->
                    requireActivity().showToastLong(exception.message.toString())
                }

        }
    }
}

