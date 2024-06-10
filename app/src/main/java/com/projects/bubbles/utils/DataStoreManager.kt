package com.projects.bubbles.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.projects.bubbles.dto.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

    suspend fun saveToken(context: Context, token: String) {
        context.dataStore.edit { preferences ->
            preferences[USER_TOKEN_KEY] = "auth=$token"
        }
    }

    fun getToken(context: Context): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[USER_TOKEN_KEY]
        }
    }

    suspend fun saveUser(context: Context, user: User) {
        context.dataStore.edit { preferences ->
            preferences[USER_ID_KEY] = user.id?.toString() ?: ""
            preferences[USERNAME_KEY] = user.username ?: ""
            preferences[EMAIL_KEY] = user.email ?: ""
            preferences[NICKNAME_KEY] = user.nickname ?: ""
        }
    }

    fun getUser(context: Context): Flow<User?> {
        return context.dataStore.data.map { preferences ->
            val id = preferences[USER_ID_KEY]?.toIntOrNull()
            val username = preferences[USERNAME_KEY] ?: ""
            val email = preferences[EMAIL_KEY] ?: ""
            val nickname = preferences[NICKNAME_KEY] ?: ""
            User(id, username, nickname, email)
        }
    }

    private val USER_ID_KEY = stringPreferencesKey("user_id")
    private val USERNAME_KEY = stringPreferencesKey("username")
    private val EMAIL_KEY = stringPreferencesKey("email")
    private val NICKNAME_KEY = stringPreferencesKey("nickname")
    private val USER_TOKEN_KEY = stringPreferencesKey("token")
}