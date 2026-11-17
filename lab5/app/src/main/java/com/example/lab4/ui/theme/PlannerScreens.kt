package com.example.lab4.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class PlannerUiState(
    val tasks: List<String> = listOf("Study for Mobile Exam", "Complete Lab 5 Report", "Review Android Navigation")
)

@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Study Planner", style = MaterialTheme.typography.headlineMedium)

        uiState.tasks.forEach { taskTitle ->
            Button(
                onClick = { onTaskClick(taskTitle) },
                modifier = modifier.fillMaxWidth()
            ) {
                Text(taskTitle)
            }
        }
    }
}

data class TaskDetailUiState(
    val title: String = ""
)

@Composable
fun TaskDetailScreen(
    uiState: TaskDetailUiState,
    onShareClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Task: ${uiState.title}", style = MaterialTheme.typography.headlineMedium)
        Button(onClick = onShareClick, modifier = modifier.fillMaxWidth()) { Text("Share") }
        OutlinedButton(onClick = onBackClick, modifier = modifier.fillMaxWidth()) { Text("Back") }
    }
}