package com.example.appleadd.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appleadd.databinding.ItemTaskBinding
import com.example.appleadd.model.Task

class TaskAdapter(private val onLongClick: (Task) -> Unit, private val onClick: (Task) -> Unit):
    RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private val data = arrayListOf<Task>()

    fun addTask(task: Task){
        data.add(0,task)
        notifyItemChanged(0)
    }

    fun addTasks(list: List<Task>){
        data.clear()
        data.addAll(list)
        data.sortByDescending { it.id }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        return TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.tvTitle.text = task.title
            binding.tvDesc.text = task.desc

            itemView.setOnLongClickListener {
                onLongClick(task)
                false
            }

            itemView.setOnClickListener {
                onClick(task)
            }

        }
    }

}