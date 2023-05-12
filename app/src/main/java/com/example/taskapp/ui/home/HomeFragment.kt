package com.example.appleadd.ui.home

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.appleadd.App
import com.example.appleadd.R
import com.example.appleadd.databinding.FragmentHomeBinding
import com.example.appleadd.model.Task
import com.example.appleadd.ui.home.adapter.TaskAdapter

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root ?: binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TaskAdapter(this::onLongClick, this::onClick)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.taskFragment)
        }


    }

    private fun onLongClick(task: Task) {

        val alertDialog = AlertDialog.Builder(requireContext())
        alertDialog.setTitle("Do you want to delete?")
        alertDialog.setNegativeButton("No", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog?.cancel()
            }
        })
        alertDialog.setPositiveButton("Yes", object : DialogInterface.OnClickListener {
            override fun onClick(dialog: DialogInterface?, which: Int) {
                App.db.taskDao().delete(task)
                val tasks = App.db.taskDao().getAll()
                adapter.addTasks(tasks)
            }
        })
        alertDialog.create().show()
    }

    private fun onClick(task: Task) {
        findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToTaskFragment(task))
    }
}