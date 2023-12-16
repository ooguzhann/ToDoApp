package com.invocatapp.todoapp.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.invocatapp.todoapp.R
import com.invocatapp.todoapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding:FragmentHomeBinding?=null
    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        viewModel.toDoList.observe(viewLifecycleOwner) {
            println(it)
        }

        binding.btnAdd.setOnClickListener {
            viewModel.insertToDo()
        }

        return  binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding
    }
}