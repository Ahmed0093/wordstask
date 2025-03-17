package com.example.presentation

import com.example.domain.wordlist.model.WordDomainModel

sealed interface ViewState {
    data class ResultUiModel(val uiModel: List<WordDomainModel>) : ViewState
    class ErrorUiModel(val error: String) : ViewState
    data object ShowLoading : ViewState
}