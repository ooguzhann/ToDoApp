package com.invocatapp.todoapp.ui.newAndEdit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.invocatapp.todoapp.R
import com.invocatapp.todoapp.databinding.FragmentNewAndEditBinding
import com.invocatapp.todoapp.model.Priority
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewAndEditFragment : Fragment(R.layout.fragment_new_and_edit) {

    private lateinit var binding: FragmentNewAndEditBinding
    private var currentPriorityIndex = 0
    private val args by navArgs<NewAndEditFragmentArgs>()
    private val viewModel by viewModels<NewAndEditViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewAndEditBinding.bind(view)

        if (args.toDoId == -1) {
            binding.fragmentNewAndEditDeleteButton.isVisible = false
            (activity as AppCompatActivity)!!.supportActionBar!!.title = "New To-Do"
        } else {
            (activity as AppCompatActivity)!!.supportActionBar!!.title = "Edit To-Do"
            viewModel.getToDo(args.toDoId)
        }

        initializeView()

        viewModel.toDoModel.observe(viewLifecycleOwner) {
            binding.fragmentNewAndEditTitleEditText.setText(it.title)
            binding.fragmentNewAndEditDescriptionEditText.setText(it.description)
            binding.fragmentNewAndEditAutoComplete.setText(it.priority?.name, false)
            binding.fragmentNewAndEditCheckbox.isChecked = it.isChecked == true

            currentPriorityIndex = when (it.priority) {
                Priority.HIGH -> 0
                Priority.MEDIUN -> 1
                else -> 2
            }
        }
    }

    private fun initializeView() {
        binding.fragmentNewAndEditSaveButton.setOnClickListener {
            handleSaveButton()
        }

        binding.fragmentNewAndEditAutoComplete.setAdapter(
            ArrayAdapter(requireContext(),android.R.layout.simple_expandable_list_item_1,
                arrayOf(Priority.HIGH.name, Priority.MEDIUN.name, Priority.LOW.name)
            )
        )

        binding.fragmentNewAndEditAutoComplete.setOnItemClickListener { _, _, index, _ ->
            currentPriorityIndex = index
        }

        binding.fragmentNewAndEditDeleteButton.setOnClickListener {
            viewModel.deleteToDo()
            Toast.makeText(requireContext(), "Deleted", Toast.LENGTH_SHORT).show()
            findNavController().popBackStack()
        }
    }

    private fun handleSaveButton() {
        val title = binding.fragmentNewAndEditTitleEditText.text.toString()
        val description = binding.fragmentNewAndEditDescriptionEditText.text.toString()
        val priority = when(currentPriorityIndex) {
            0 -> Priority.HIGH
            1 -> Priority.MEDIUN
            else -> Priority.LOW
        }

        if (args.toDoId == -1 ) {
            viewModel.insertToDo(title,description,binding.fragmentNewAndEditCheckbox.isChecked, priority)
        } else {
            viewModel.updateToDo(title,description,binding.fragmentNewAndEditCheckbox.isChecked, priority)
        }

        Toast.makeText(requireContext(), "Successfully Saved", Toast.LENGTH_SHORT).show()
        findNavController().popBackStack() // insert olunca ana ekrana geri dönecek
    }


}