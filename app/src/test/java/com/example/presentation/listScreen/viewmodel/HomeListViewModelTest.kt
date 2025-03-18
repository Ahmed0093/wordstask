package com.example.presentation.listScreen.viewmodel

import com.example.domain.wordlist.model.WordDomainModel
import com.example.domain.wordlist.usecase.GetWordsListUseCase
import com.example.presentation.ViewState
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.wheneverBlocking

class HomeListViewModelTest {

    lateinit var homeListViewModel: HomeListViewModel

    private val getWordsListUseCase: GetWordsListUseCase =
        Mockito.mock(GetWordsListUseCase::class.java)

    @Before
    fun setUp() {
        homeListViewModel = HomeListViewModel(
            dispatcher = TestDispatcher(),
            getWordsListUseCase = getWordsListUseCase
        )
    }

    @Test
    fun `when fetchWordList call check Success Response`() = runTest {

        val domainModel = WordDomainModel("n1", 2)

        wheneverBlocking { getWordsListUseCase.invoke() }.then { (listOf(domainModel)) }
        homeListViewModel.fetchWordsList()

        val res = homeListViewModel.uiState.value as ViewState.ResultUiModel
        assert(res.uiModel[0].name == domainModel.name)
    }

    @Test
    fun `when fetchWordList throws exception check failure Response`() = runTest {

        wheneverBlocking { getWordsListUseCase.invoke() }.then { throw Exception() }
        homeListViewModel.fetchWordsList()

        assert(homeListViewModel.uiState.value is ViewState.ErrorUiModel)
    }
}