package com.example.manager.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.manager.App
import com.example.manager.databinding.FragmentTaskBinding
import com.example.manager.model.Task

class TaskFragment : Fragment() {

    private lateinit var binding: FragmentTaskBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            if (binding.etTitle.text.toString().isNotEmpty()) {
                findNavController().navigateUp()
            } else {
                Toast.makeText(context, "ERROR", Toast.LENGTH_SHORT).show()
                binding.etTitle.error = "ERROR"
                return@setOnClickListener
            }
            val data = Task(
                title = binding.etTitle.text.toString(),
                desc = binding.etDesc.text.toString()
            )
            App.db.taskDao().insert(data)
//            findNavController().navigateUp()
        }
    }

}