package com.example.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todoapp.databinding.ActivityMainBinding
import com.example.todoapp.databinding.ItemTodoBinding

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val emptyMutableList = mutableListOf<Todo>()

        todoAdapter = TodoAdapter(emptyMutableList)

        binding.rvTodoItems.adapter = todoAdapter

        binding.rvTodoItems.layoutManager = LinearLayoutManager(this)

        binding.btnAddTodo.setOnClickListener {
            val todoTitle = binding.etTodoTitle.text.toString()

            if (todoTitle.isNotEmpty()) {
                val todo = Todo(todoTitle)
                todoAdapter.addTodo(todo)
                binding.etTodoTitle.text.clear()
            }
        }

        binding.btnDeleteTodo.setOnClickListener {
            todoAdapter.deleteTodo()
        }
    }
}