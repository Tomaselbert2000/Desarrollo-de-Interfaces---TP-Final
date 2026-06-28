package com.example.dditpgrupal.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Message
import com.example.dditpgrupal.data.dummyMessages
import com.example.dditpgrupal.ui.components.MessageCard

@Suppress("ktlint:standard:function-naming")
enum class MessageView { LIST, NEW_MESSAGE, RESPONSE }

@Suppress("ktlint:standard:function-naming")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessageScreen(messages: List<Message> = dummyMessages) {
    var currentScreen by remember { mutableStateOf(MessageView.LIST) }
    var selectedMessage by remember { mutableStateOf<Message?>(null) }

    if (currentScreen == MessageView.RESPONSE && selectedMessage != null) {
        MessageResponseScreen(
            message = selectedMessage!!,
            onBackClick = { currentScreen = MessageView.LIST },
        )
        return
    }

    if (currentScreen == MessageView.NEW_MESSAGE) {
        NewMessageScreen(onBackClick = { currentScreen = MessageView.LIST })
        return
    }

    val messagesBg by animateColorAsState(
        targetValue = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.26f),
        animationSpec = tween(400),
        label = "messagesBg",
    )

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { currentScreen = MessageView.NEW_MESSAGE }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nuevo mensaje",
                )
            }
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().background(messagesBg).padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(messages, key = { it.subject + it.date }) { message ->
                MessageCard(
                    message = message,
                    onClick = {
                        selectedMessage = it
                        currentScreen = MessageView.RESPONSE
                    },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun MessageScreenPreview() {
    MessageScreen()
}
