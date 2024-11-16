package com.reference.locationbasednotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.reference.locationbasednotes.ui.home.HomeScreen
import com.reference.locationbasednotes.ui.home.HomeViewModel
import com.reference.locationbasednotes.ui.splash.SplashScreen
import com.reference.locationbasednotes.ui.splash.SplashViewModel
import com.reference.locationbasednotes.ui.theme.LocationBasedNotesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocationBasedNotesTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        // Splash Screen
        composable("splash") {
            SplashScreen(viewModel = hiltViewModel<SplashViewModel>(),
                navigateToMainScreen = {
                    navController.navigate("home") {
                        popUpTo("splash") { inclusive = true } // Remove splash from backstack
                    }
                }
            )
        }

        // Home Screen
        composable("home") {
            HomeScreen(
                viewModel = hiltViewModel<HomeViewModel>(),
                navigateToNoteDetails = { noteId ->
                    navController.navigate("noteDetails/$noteId")
                },
                navigateToAddEditNote = {
                    navController.navigate("addEditNote")
                },
                navigateToMap = {
                    navController.navigate("map")
                }
            )
        }

//        // Note Details Screen
//        composable("noteDetails/{noteId}") { backStackEntry ->
//            val noteId = backStackEntry.arguments?.getString("noteId")?.toInt() ?: 0
//            NoteDetailsScreen(noteId = noteId)
//        }
//
//        // Add/Edit Note Screen
//        composable("addEditNote") {
//            AddEditNoteScreen()
//        }
//
//        // Map Screen
//        composable("map") {
//            MapScreen()
//        }
    }
}
