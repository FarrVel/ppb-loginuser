package com.example.loginuser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.loginuser.data.local.AppDatabase
import com.example.loginuser.repository.UserRepository
import com.example.loginuser.ui.screen.LoginScreen
import com.example.loginuser.ui.theme.LoginUserTheme // Pastikan nama ini sesuai dengan tema bawaan project Anda
import com.example.loginuser.viewmodel.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val database = AppDatabase.getDatabase(this)

        val repository = UserRepository(database.userDao())

        val factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return LoginViewModel(repository) as T
            }
        }

        val viewModel = ViewModelProvider(this, factory)[LoginViewModel::class.java]

        setContent {
            LoginUserTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginScreen(viewModel = viewModel)
                }
            }
        }
    }
}