package com.example.manager.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.manager.App
import com.example.manager.R
import com.example.manager.databinding.FragmentHomeBinding
import com.example.manager.model.Task
import com.example.manager.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val adapter = TaskAdapter(this::onLongClick)

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = adapter
        setData()
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }
    }

    private fun onLongClick(task: Task) {
        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setMessage(getString(R.string.delete_titlle))
            .setTitle(getString(R.string.deletemessage))
            .setNegativeButton(
                getString(R.string.yess)
            ) { dialog, _ -> dialog?.cancel() }
            .setPositiveButton(
                getString(R.string.nope)
            ) { _, _ ->
                App.db.taskDao().delete(task)
                setData()
            }
            .show()
    }

    private fun setData() {
        val tasks = App.db.taskDao().getAll()
        adapter.addData(tasks)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
