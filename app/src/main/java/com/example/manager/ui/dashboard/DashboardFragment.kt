package com.example.manager.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.manager.databinding.FragmentDashboardBinding
import com.example.manager.model.Car
import com.example.manager.utills.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firestore.v1.FirestoreGrpc.FirestoreStub

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val db: FirebaseFirestore by lazy{
        FirebaseFirestore.getInstance()
    }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCarSave.setOnClickListener {
            val data = Car(
                brand = binding.etCarName.text.toString(),
                model = binding.etModel.text.toString(),
            )

            db.collection(FirebaseAuth.getInstance().currentUser?.uid.toString())
                .add(data).addOnSuccessListener {
                    binding.etCarName.text?.clear()
                    binding.etModel.text?.clear()
                    showToast("Успешно сохранено")
                }.addOnFailureListener{
                    showToast(it.message.toString())
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}