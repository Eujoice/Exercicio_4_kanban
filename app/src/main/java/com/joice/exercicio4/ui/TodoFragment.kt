package com.joice.exercicio4.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.joice.exercicio4.R
import com.joice.exercicio4.databinding.FragmentHomeBinding
import com.joice.exercicio4.databinding.FragmentTodo2Binding

class TodoFragment : Fragment() {

    private var _binding: FragmentTodo2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodo2Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}