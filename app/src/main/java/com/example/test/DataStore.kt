package com.example.test

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.test.retrofit.ApiInterface
import com.example.test.retrofit.RetrofitClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object DataStore {
    val Context.userPreferencesDataStore: DataStore<Preferences> by preferencesDataStore(
        name = "welcome"
    )
}