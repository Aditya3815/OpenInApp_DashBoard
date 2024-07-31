package com.example.openinapp_dashboard.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.openinapp_dashboard.adapter.TopLinkAdapter
import com.example.openinapp_dashboard.databinding.FragmentTopLinkBinding
import com.example.openinapp_dashboard.repository.TopLinkRepository
import com.example.openinapp_dashboard.viewmodel.TopLinkViewModel

class TopLinkFragment : Fragment() {
    private lateinit var viewModel: TopLinkViewModel
    private lateinit var adapter: TopLinkAdapter
    private var _binding: FragmentTopLinkBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTopLinkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = TopLinkRepository()
        viewModel = ViewModelProvider(
            this,
            TopLinkViewModelFactory(repository)
        )[TopLinkViewModel::class.java]

        adapter = TopLinkAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.topLinks.observe(viewLifecycleOwner) { links ->
            adapter.submitList(links)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }

        viewModel.fetchTopLinks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class TopLinkViewModelFactory(private val repository: TopLinkRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TopLinkViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TopLinkViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}