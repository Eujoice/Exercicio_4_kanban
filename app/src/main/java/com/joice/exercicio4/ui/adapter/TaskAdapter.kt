package com.joice.exercicio4.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.joice.exercicio4.R
import com.joice.exercicio4.data.model.Status
import com.joice.exercicio4.data.model.Task
import com.joice.exercicio4.databinding.ItemTaskBinding

class TaskAdapter(
    private val context: Context,
    private val taskList: List<Task>,
    private val taskSelected: (Task, Int) -> Unit
): RecyclerView.Adapter<TaskAdapter.MyViewHolder>() {

    companion object {
        val SELECTED_BACK: Int = 1
        val SELECTED_REMOVER: Int = 2
        val SELECTED_EDIT: Int = 3
        val SELECTED_DETAILS: Int = 4
        val SELECTED_NEXT: Int = 5
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount() = taskList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val task = taskList[position]
        holder.binding.textDescription.text = task.description

        setIndicators(task, holder)
    }

    private fun setIndicators(task: Task, holder: MyViewHolder) {
        when (task.status) {
            Status.TODO -> {
                holder.binding.buttonBack.isVisible = false
            }
            Status.DOING -> {
                holder.binding.buttonBack.setColorFilter(ContextCompat.getColor(context, R.color.color_status_todo))
                holder.binding.buttonForward.setColorFilter(ContextCompat.getColor(context, R.color.color_status_done))
            }
            Status.DONE -> {
                holder.binding.buttonForward.isVisible = false
            }
        }
    }

    inner class MyViewHolder(val binding : ItemTaskBinding): RecyclerView.ViewHolder(binding.root) {
    }

}
