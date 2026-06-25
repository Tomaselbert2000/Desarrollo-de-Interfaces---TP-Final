package com.example.dditpgrupal.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.dditpgrupal.data.ClassModule
import com.example.dditpgrupal.data.dummyModuleList
import com.example.dditpgrupal.ui.components.ClassModuleCard

@Suppress("ktlint:standard:function-naming")
@Composable
fun ClassModuleListScreen(moduleList: List<ClassModule> = dummyModuleList) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(moduleList, key = { it.moduleName }) { module ->
            ClassModuleCard(module = module)
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview
@Composable
private fun ClassModuleListScreenPreview() {
    ClassModuleListScreen(moduleList = dummyModuleList)
}
