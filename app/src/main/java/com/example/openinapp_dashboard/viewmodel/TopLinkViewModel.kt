package com.example.openinapp_dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openinapp_dashboard.models.TopLink
import com.example.openinapp_dashboard.repository.TopLinkRepository
import kotlinx.coroutines.launch

class TopLinkViewModel(private val repository: TopLinkRepository) : ViewModel() {
    private val _topLinks = MutableLiveData<List<TopLink>>()
    val topLinks: LiveData<List<TopLink>> = _topLinks

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun fetchTopLinks() {
        viewModelScope.launch {
            try {
                val links = repository.getTopLinks()
                _topLinks.value = links
            } catch (e: Exception) {
                _error.value = "Error fetching top links: ${e.message}"
            }
        }
    }
}