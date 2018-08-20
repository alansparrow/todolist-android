package com.baotrungtn.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

/**
 * Created by baotrungtn@gmail.com on 21.August.2018
 **/


class ListItemsRecyclerViewAdapter(var taskList: TaskList) : RecyclerView.Adapter<ListItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.task_view_holder, parent, false)
        return ListItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return taskList.tasks.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        if (holder != null) {
            holder.taskTextView.text = taskList.tasks[position]
        }
    }

    fun addItem(task: String) {
        taskList.tasks.add(task)
        notifyDataSetChanged()
    }

}