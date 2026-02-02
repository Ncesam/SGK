package com.ncesam.sgk2026.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.ncesam.sgk2026.data.remote.AuthApi
import com.ncesam.sgk2026.data.remote.HotelApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.dsl.module
import retrofit2.Retrofit

private const val PREFERENCES_FILENAME = "settings.preferences_pb"

val ApiModule = module {
	single<DataStore<Preferences>> {
		PreferenceDataStoreFactory.create(
			corruptionHandler = ReplaceFileCorruptionHandler {
				emptyPreferences()
			},
			scope = CoroutineScope(Dispatchers.IO + SupervisorJob()),
			produceFile = {
				get<Context>().preferencesDataStoreFile(PREFERENCES_FILENAME)
			}
		)
	}
	single<AuthApi> { get<Retrofit>().create(AuthApi::class.java) }
	single<HotelApi> {get<Retrofit>().create(HotelApi::class.java)}
}