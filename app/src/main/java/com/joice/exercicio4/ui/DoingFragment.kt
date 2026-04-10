package com.joice.exercicio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.joice.exercicio4.R
import com.joice.exercicio4.data.model.Status
import com.joice.exercicio4.data.model.Task
import com.joice.exercicio4.databinding.FragmentDoingBinding
import com.joice.exercicio4.databinding.FragmentHomeBinding
import com.joice.exercicio4.ui.adapter.TaskAdapter


class DoingFragment : Fragment() {

    private var _binding: FragmentDoingBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewTask(getTask())
    }

    private fun initRecyclerViewTask(taskList: List<Task>) {

        taskAdapter = TaskAdapter(requireContext(), taskList)
        binding.recyclerViewTaskDoing.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTaskDoing.setHasFixedSize(true)

        binding.recyclerViewTaskDoing.adapter = taskAdapter
    }

    private fun getTask() = listOf(
        Task("15", "Iniciar programação Mobile", Status.DOING),
        Task("16", "Conexão com o banco de dados", Status.DOING),
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}