package com.example.lab4

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab4.ui.theme.Lab4Theme

// 1. Data Class للحالة
data class PlannerUiState(
    val studentName: String = "Rahgad",
    val totalTasks: Int = 7,
    val doneTasks: Int = 3,
    val isCompleted: Boolean = false
)

// 2. الشاشة الأساسية (Stateless)
@Composable
fun PlannerHomeScreen(
    uiState: PlannerUiState,
    onTaskFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = "Welcome, ${uiState.studentName}!")
        Text(text = "Study Progress: ${uiState.doneTasks} of ${uiState.totalTasks} done")

        Button(
            onClick = onTaskFinished,
            enabled = !uiState.isCompleted
        ) {
            Text(text = if (uiState.isCompleted) "All Tasks Completed" else "Complete a Task")
        }
    }
}

// 3. الـ Route لإدارة الحالة (عدلناها هنا إلى fun الصحيحة)
@Composable
fun PlannerHomeRoute() {
    var uiState by remember { mutableStateOf(PlannerUiState()) }

    PlannerHomeScreen(
        uiState = uiState,
        onTaskFinished = {
            if (uiState.doneTasks < uiState.totalTasks) {
                val newDone = uiState.doneTasks + 1
                uiState = uiState.copy(
                    doneTasks = newDone,
                    isCompleted = newDone >= uiState.totalTasks
                )
            }
        }
    )
}

// 4. Previews
@Preview(showBackground = true, name = "Not done yet")
@Composable
private fun PlannerPreviewNotDone() {
    Lab4Theme {
        PlannerHomeScreen(
            uiState = PlannerUiState("Rahgad", 7, 3, false),
            onTaskFinished = {}
        )
    }
}

@Preview(showBackground = true, name = "All done")
@Composable
private fun PlannerPreviewCompleted() {
    Lab4Theme {
        PlannerHomeScreen(
            uiState = PlannerUiState("Rahgad", 7, 7, true),
            onTaskFinished = {}
        )
    }
}