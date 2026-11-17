package com.example.lab4

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.lab4.navigation.PlannerHome
import com.example.lab4.navigation.TaskDetail
import com.example.lab4.ui.PlannerHomeScreen
import com.example.lab4.ui.PlannerUiState
import com.example.lab4.ui.TaskDetailScreen
import com.example.lab4.ui.TaskDetailUiState

@Composable
fun StudyPlannerApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = PlannerHome) {
        composable<PlannerHome> {
            PlannerHomeScreen(
                uiState = PlannerUiState(),
                onTaskClick = { taskTitle ->
                    navController.navigate(TaskDetail(title = taskTitle))
                }
            )
        }

        composable<TaskDetail> { backStackEntry ->
            val detail: TaskDetail = backStackEntry.toRoute()
            val context = LocalContext.current

            TaskDetailScreen(
                uiState = TaskDetailUiState(title = detail.title),
                onShareClick = {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        putExtra(Intent.EXTRA_TEXT, "Today I'm studying: ${detail.title}")
                        type = "text/plain"
                    }
                    context.startActivity(Intent.createChooser(sendIntent, "Share via"))
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}