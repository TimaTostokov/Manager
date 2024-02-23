package com.example.manager.ui.auth.code

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.manager.R
import com.example.manager.databinding.FragmentCodeBinding
import com.example.manager.ui.auth.phone.PhoneFragment
import com.example.manager.utills.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class CodeFragment : Fragment() {

    private lateinit var binding: FragmentCodeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCodeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val verID = arguments?.getString(PhoneFragment.VER_ID_KEY)
        binding.btnAccept.setOnClickListener {
            val credential =
                PhoneAuthProvider.getCredential(verID!!, binding.etCoding.text.toString())
            signInWithPhoneAuthCredential(credential)
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnSuccessListener {
                showToast(getString(R.string.auth_success_mesg))
                findNavController().navigate(R.id.navigation_home)
            }
            .addOnFailureListener {
                showToast(it.message.toString())
            }
    }

}