package com.joice.exercicio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.joice.exercicio4.R
import com.joice.exercicio4.data.model.Status
import com.joice.exercicio4.data.model.Task
import com.joice.exercicio4.databinding.FragmentHomeBinding
import com.joice.exercicio4.databinding.FragmentTodo2Binding
import com.joice.exercicio4.ui.adapter.TaskAdapter
import com.joice.exercicio4.ui.auth.FirebaseHelper

class TodoFragment : Fragment() {

    private var _binding: FragmentTodo2Binding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodo2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRecyclerViewTask()
        getTask()
    }

    private fun initListeners() {
        binding.floatingActionButton.setOnClickListener {
            findNavController().navigate((R.id.action_homeFragment_to_formTaskFragment))
        }
    }

    private fun initRecyclerViewTask() {

        taskAdapter = TaskAdapter(requireContext()) {task, option -> optionSelected(task, option)}
        with(binding.recyclerViewTask) {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = taskAdapter
        }
    }

    private fun optionSelected(task: Task, option: Int) {
        when(option){
            TaskAdapter.SELECTED_REMOVER -> {
                Toast.makeText(requireContext(), "Removendo ${task.description}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECTED_EDIT -> {
                Toast.makeText(requireContext(), "Editando ${task.description}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECTED_DETAILS -> {
                Toast.makeText(requireContext(), "Detalhes ${task.description}", Toast.LENGTH_SHORT).show()
            }

            TaskAdapter.SELECTED_NEXT -> {
                task.status = Status.DOING
                updateTask(task)
            }
        }
    }
    private fun getTask() {
        val taskList = listOf(
            Task("0", "Criar nova tela do app", Status.TODO),
            Task("1", "Validar informações na tela de login", Status.TODO),
            Task("2", "Adicionar novas funcionalidades no app", Status.TODO),
            Task("3", "Salvar token localmente", Status.TODO),
            Task("4", "Criar funcionalidade de logout no app", Status.TODO),
        )
        taskAdapter.submitList(taskList)
    }

    private fun deleteTask(task: Task) {
        FirebaseHelper.getDatabase()
            .child("task")
            .child(FirebaseHelper.getIdUser())
            .child(task.id)
            .removeValue().addOnCompleteListener { result ->
                if(result.isSuccessful) {
                    Toast.makeText(requireContext(), R.string.text_delete_success_task, Toast.LENGTH_SHORT).show()
                    val oldList = taskAdapter.currentList
                    val newList = oldList.toMutableList().apply { remove(task) }
                    taskAdapter.submitList(newList)
                } else {
                    Toast.makeText(requireContext(), R.string.error_generic, Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun updateTask(task: Task) {
        FirebaseHelper.getDatabase()
            .child("task")
            .child(FirebaseHelper.getIdUser())
            .child(task.id)
            .setValue(task).addOnCompleteListener { setFragmentResult() ->
                if(result.isSuccessful) {
                    Toast.makeText(requireContext(), R.string.text_save_sucess_form_task_fragment, Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), R.string.error_generic, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}