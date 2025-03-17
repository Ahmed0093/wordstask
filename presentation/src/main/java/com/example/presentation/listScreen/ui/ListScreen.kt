package com.example.presentation.listScreen.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.domain.wordlist.model.WordDomainModel
import com.example.presentation.R
import com.example.presentation.ViewState
import com.example.presentation.components.AppBar
import com.example.presentation.components.CircleLoaderUI
import com.example.presentation.components.ErrorUI
import com.example.presentation.components.MySearchBar
import com.example.presentation.listScreen.viewmodel.HomeListViewModel

@Composable
fun HomeListScreen() {

    val homeListViewModel: HomeListViewModel = hiltViewModel()
    val uiState by homeListViewModel.uiState.collectAsState()
    val searchQuery by homeListViewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp)
    ) {
        AppBar(
            title = stringResource(R.string.app_name),
            onLeadingIconClick = {
            },
            trailingIcon = {
                Text(
                    text = stringResource(R.string.sort),
                    color = Color.Blue, modifier = Modifier.padding(top = 20.dp)
                )
            }, onTrailingIconClick = {
                homeListViewModel.sortDescending()
            })
        MySearchBar(
            query = searchQuery,
            onQueryChange = { query ->
                homeListViewModel.onSearchQueryChanged(query)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp)
        )

        when (val state = uiState) {

            is ViewState.ShowLoading -> {
                CircleLoaderUI()

            }

            is ViewState.ResultUiModel -> {
                val wordsListModel = state.uiModel
                ListUsersContent(wordsListModel)
            }

            is ViewState.ErrorUiModel -> {
                ErrorUI {
                    homeListViewModel.fetchWordsList()
                }

            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun ListUsersContent(wordsListModel: List<WordDomainModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            contentPadding = PaddingValues(16.dp)
        ) {
            items(wordsListModel) { words ->
                Card(
                    modifier = Modifier.background(
                        Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = Color.Transparent
                            )
                            .padding(
                                10.dp
                            )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(
                                    text = words.name + "  " + "count= " + words.repeatedCount,
                                    style = MaterialTheme.typography.labelLarge
                                )

                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
