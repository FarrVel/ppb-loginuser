package com.example.loginuser.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.loginuser.data.local.User
import com.example.loginuser.repository.UserRepository
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: UserRepository) : ViewModel() {

    var loginState by mutableStateOf<String?>(null)
        private set

    fun insertDummyUser() {
        viewModelScope.launch {
            try {
                repository.insert(User(username = "admin", password = "12345"))
            } catch (e: Exception) {
            }
        }
    }

    fun login(username: String, password: String) {
        if (username.isBlank() || password.isBlank()) {
            loginState = "Username dan Password tidak boleh kosong!"
            return
        }

        viewModelScope.launch {
            val user = repository.login(username, password)
            loginState = if (user != null) {
                "Login Berhasil\nSelamat datang, ${user.username}!"
            } else {
                "Username atau Password Salah\nCoba lagi!"
            }
        }
    }
}