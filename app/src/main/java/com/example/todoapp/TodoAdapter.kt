package com.example.todoapp

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.databinding.ItemTodoBinding

class TodoAdapter(
    private  var   binding: ItemTodoBinding,
    private var todos: MutableList<Todo>


) : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {
    class TodoViewHolder(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(

            LayoutInflater.from(parent.context).inflate(
                R.layout.item_todo,
                parent,
                false
            )
        )
    }

    fun addTodo(todo: Todo) {
        todos.add(todo)
        notifyItemInserted(todos.size - 1)
    }

    fun deleteTodo() {
        todos.removeAll { todo ->
            todo.isDone
        }
        notifyDataSetChanged()
    }

    private fun strikeThroughToggle(tvTodoTitle: TextView, isDone: Boolean) {
        if (isDone) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        val curTodo = todos[position]
        holder.itemView.apply {
            binding.tvTodoTitle.text = curTodo.title
            binding.cbDone.isChecked = curTodo.isDone
            strikeThroughToggle(binding.tvTodoTitle, curTodo.isDone)
            binding.cbDone.setOnCheckedChangeListener { _, isChecked ->
                strikeThroughToggle(binding.tvTodoTitle, isChecked)
                curTodo.isDone = !curTodo.isDone
            }
        }
    }

    override fun getItemCount(): Int {
        return todos.size
    }
}