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
import com.joice.exercicio4.databinding.FragmentDoneBinding
import com.joice.exercicio4.ui.adapter.TaskAdapter

class DoneFragment : Fragment() {

    private var _binding: FragmentDoneBinding? = null
    private val binding get() = _binding!!

    private lateinit var taskAdapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDoneBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerViewTask(getTask())
    }

    private fun initRecyclerViewTask(taskList: List<Task>) {

        taskAdapter = TaskAdapter(requireContext(), taskList)
        binding.recyclerViewTaskDone.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewTaskDone.setHasFixedSize(true)

        binding.recyclerViewTaskDone.adapter = taskAdapter
    }

    private fun getTask() = listOf(
        Task("10", "System Design", Status.DONE),
        Task("11", "Fazer barra de pesquisa funcionar", Status.DONE),
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}