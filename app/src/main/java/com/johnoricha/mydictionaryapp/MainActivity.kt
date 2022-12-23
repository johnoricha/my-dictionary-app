package com.johnoricha.mydictionaryapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.johnoricha.mydictionaryapp.feature.dictionary.presentation.WordInfoItem
import com.johnoricha.mydictionaryapp.feature.dictionary.presentation.WordInfoState
import com.johnoricha.mydictionaryapp.feature.dictionary.presentation.WordInfoViewModel
import com.johnoricha.mydictionaryapp.feature.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DictionaryTheme {
                val viewModel: WordInfoViewModel = hiltViewModel()
                val state = viewModel.state
                val scaffoldState = rememberScaffoldState()

                val wordInfoFlowLifecycleAware = remember(state, this) {
                    state.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                }

                LaunchedEffect(key1 = true) {
                    viewModel.eventFlow.collectLatest { event ->
                        when (event) {
                            is WordInfoViewModel.UIEvent.ShowSnackbar -> {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    message = event.message
                                )
                            }
                        }
                    }
                }

                val wordInfoState by wordInfoFlowLifecycleAware.collectAsState(initial = WordInfoState())

                Scaffold(
                    scaffoldState = scaffoldState
                ) {

                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colors.background)
                            .padding(it)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier.fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(wordInfoState.wordInfoItems.size) { i ->
                                    val wordInfo = wordInfoState.wordInfoItems[i]
                                    if (i > 0) {
                                        Spacer(modifier = Modifier.height(8.dp))
                                    }
                                    WordInfoItem(wordInfo = wordInfo)
                                    if (i < wordInfoState.wordInfoItems.size - 1) {
                                        Divider()
                                    }
                                }
                            }
                        }
                        if (wordInfoState.isLoading) {
                            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }
            }

        }
    }
}