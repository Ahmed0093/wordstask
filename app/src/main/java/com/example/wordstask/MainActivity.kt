package com.example.wordstask

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
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
import com.example.wordstask.data.model.WordsModel
import com.example.wordstask.ui.AppBar
import com.example.wordstask.ui.CircleLoaderUI
import com.example.wordstask.ui.ErrorUI
import com.example.wordstask.ui.ViewState
import com.example.wordstask.ui.theme.WordstaskTheme
import com.example.wordstask.ui.viewmodel.HomeListViewModel
import com.example.wordtask.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WordstaskTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeListScreen()
                }
            }
        }
    }
}

@Composable
fun HomeListScreen() {

    val homeListViewModel: HomeListViewModel = hiltViewModel()
    val uiState by homeListViewModel.uiState.collectAsState()


    AppBar(
        title = stringResource(R.string.app_name),
        onLeadingIconClick = {
        },
        trailingIcon = {
            Text(
                text = stringResource(R.string.refresh),
                color = Color.Blue, modifier = Modifier.padding(top = 15.dp)
            )
        }, onTrailingIconClick = {
            homeListViewModel.fetchWordsList()
        })
    Spacer(modifier = Modifier.height(16.dp))
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

            }

        }
    }

    Spacer(modifier = Modifier.height(16.dp))
}


@Composable
fun ListUsersContent(wordsListModel: List<WordsModel>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp),
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
//            }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}

