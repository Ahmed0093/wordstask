package com.example.presentation.listScreen.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.wordlist.model.WordDomainModel
import com.example.domain.wordlist.usecase.GetWordsListUseCase
import com.example.presentation.CoroutineDispatcher
import com.example.presentation.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeListViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val getWordsListUseCase: GetWordsListUseCase
) : ViewModel() {
    private val _uiState =
        MutableStateFlow<ViewState>(ViewState.ShowLoading)
    val uiState = _uiState.asStateFlow()
    private var isDescending = false

    // Search query
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()
    val handler = CoroutineExceptionHandler { _, exception ->
        _uiState.value = ViewState.ErrorUiModel(exception.message ?: "Error")
    }

    // Original list of words
    private var originalWordsList: List<WordDomainModel> = emptyList()

    init {
        fetchWordsList()
    }

    fun fetchWordsList() {
        viewModelScope.launch(dispatcher.IO + handler) {
            _uiState.value = ViewState.ShowLoading
            originalWordsList = getWordsListUseCase.invoke()
            _uiState.value = ViewState.ResultUiModel(originalWordsList)

        }
    }

    fun sortDescending() {
        val currentState = _uiState.value
        if (currentState is ViewState.ResultUiModel) {
            val sortedList = if (isDescending) {
                currentState.uiModel.sortedBy { it.repeatedCount }
            } else {
                currentState.uiModel.sortedByDescending { it.repeatedCount }
            }
            _uiState.value = ViewState.ResultUiModel(sortedList)
            isDescending = !isDescending
        }
    }

    // Update search query and filter the list
    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        filterWordsList(query)
    }

    // Filter words list based on search query
    private fun filterWordsList(query: String) {
        val filteredList = if (query.isEmpty()) {
            originalWordsList // Return the original list if the query is empty
        } else {
            originalWordsList.filter { word ->
                word.name.contains(query, ignoreCase = true) // Case-insensitive search
            }
        }
        _uiState.value = ViewState.ResultUiModel(filteredList)
    }
}
