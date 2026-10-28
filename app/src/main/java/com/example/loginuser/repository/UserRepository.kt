package com.example.loginuser.repository

import com.example.loginuser.data.local.User
import com.example.loginuser.data.local.UserDao

class UserRepository(private val dao: UserDao) {

    suspend fun insert(user: User) {
        dao.insert(user)
    }

    suspend fun login(username: String, password: String): User? {
        return dao.login(username, password)
    }
}