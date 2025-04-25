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
import com.example.samacharapp.MainActivity
import com.example.samacharapp.databinding.LoginFragmentBinding
import com.google.firebase.auth.FirebaseAuth

class Loginfragment : Fragment() {
    lateinit var binding: LoginFragmentBinding

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
    ): View {
        binding = LoginFragmentBinding.inflate(inflater, container, false)
        setListeners()
        return binding.root
    }

    private fun setListeners() = binding.apply {
        login.setOnClickListener {
            validate()
        }

        signUpText.setOnClickListener {
            (requireActivity() as HomeActivity).replaceFragment(Signupfragment())
        }
    }

    //validate
    private fun validate() = binding.apply {
        val email = userMail.text.toString()
        val password = passWord.text.toString()

        // Email Validation
        if (email.isEmpty()) {
            userMail.error = "Email is Empty"
            return@apply
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            userMail.error = "Email is not valid"
            return@apply
        }

        // Password Validation
        if (password.isEmpty()) {
            passWord.error = "Password is Empty"
            return@apply
        } else if (password.length != 8) {
            passWord.error = "Password must be of 8 characters"
            return@apply
        }

        // If validation passes, clear the fields and proceed to register user
        userMail.text.clear()
        passWord.text.clear()

        // Firebase Authentication to log in an existing user
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { result ->
                if (result.isSuccessful) {
                    requireActivity().showToastLong("User Logged In Successfully")
                    requireActivity().goToActivity(MainActivity::class.java)  // Redirect to MainActivity after login
                } else {
                    requireActivity().showToastLong("Authentication Failed")  // Show failure message if login fails
                }
            }
            .addOnFailureListener { exception ->
                requireActivity().showToastLong(exception.message.toString())  // Show error message if any failure occurs
            }

    }

}








