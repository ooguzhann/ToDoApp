package com.invocatapp.todoapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowId
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.invocatapp.todoapp.databinding.TodoItemListBinding
import com.invocatapp.todoapp.model.ToDoModel

class HomeListAdapter(private val toDoClickListener: ToDoClickListener): ListAdapter<ToDoModel, HomeListAdapter.ViewHolder>(ToDoDiffCallBack()) {
    class ViewHolder(private val binding:TodoItemListBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(toDoClickListener: ToDoClickListener, toDoModel: ToDoModel) {
            binding.toDoModel = toDoModel

            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val binding = TodoItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return  ViewHolder(binding)
            }
        }

    }

    private class ToDoDiffCallBack: DiffUtil.ItemCallback<ToDoModel>() {
        override fun areItemsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ToDoModel, newItem: ToDoModel): Boolean {
            return oldItem.areContentsTheSame(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(toDoClickListener, currentList[position])
    }

}

interface ToDoClickListener {
    fun onToDoClick(id: Int)
    fun onToDoChecked(toDoModel: ToDoModel)
}




