package com.example.test.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.test.MainApplication
import com.example.test.datastore.DataStore.userPreferencesDataStore
import kotlinx.coroutines.flow.first



object DataStore {
    val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "welcome"
    )

    suspend fun save(key: String, value: Int) {
        val dataStoreKey = intPreferencesKey(key)
        MainApplication.instance().userPreferencesDataStore.edit { settings ->
            settings[dataStoreKey] = value
        }
    }

    suspend fun read(key: String): Int? {
        val dataStoreKey = intPreferencesKey(key)
        val preferences = MainApplication.instance().userPreferencesDataStore.data.first()
        return preferences[dataStoreKey]
    }

}