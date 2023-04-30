package com.example.products.data.datastore

import androidx.datastore.preferences.core.booleanPreferencesKey
import kotlinx.coroutines.flow.Flow

interface DataStoreManager {
    val isLoggedIn: Flow<Boolean>
    suspend fun setIsLoggedIn(isLoggedIn: Boolean)
    var token: String

    companion object {
        const val DARK_THEME_KEY = "dark_theme"
        val LOGGED_IN_KEY = booleanPreferencesKey("logged_in_key")
        const val TOKEN_KEY = "token_key"
    }
}