package com.example.dditpgrupal.ui.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.Message
import com.example.dditpgrupal.data.dummyMessages
import com.example.dditpgrupal.ui.components.MessageCard

@Suppress("ktlint:standard:function-naming")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MessagesScreen(messages: List<Message> = dummyMessages) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        items(messages, key = { it.subject + it.date }) { message ->
            MessageCard(message = message)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
private fun MessagesScreenPreview() {
    MessagesScreen()
}