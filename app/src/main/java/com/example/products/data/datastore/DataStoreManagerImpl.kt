package com.example.products.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.products.data.common.get
import com.example.products.data.common.set
import com.example.products.data.datastore.DataStoreManager.Companion.LOGGED_IN_KEY
import com.example.products.data.datastore.DataStoreManager.Companion.TOKEN_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DataStoreManagerImpl @Inject constructor(var dataStore: DataStore<Preferences>) :
    DataStoreManager {

    override val isLoggedIn: Flow<Boolean> = dataStore.data.map {
        it[LOGGED_IN_KEY] ?: false
    }

    override suspend fun setIsLoggedIn(isLoggedIn: Boolean) {
        dataStore.edit {
            it[LOGGED_IN_KEY] = isLoggedIn
        }
    }

    override var token: String by StringPreference(dataStore, TOKEN_KEY, "")

    class StringPreference(
        private val dataStore: DataStore<Preferences>,
        private val key: String,
        private val defaultValue: String?
    ) : ReadWriteProperty<Any, String> {
        override fun getValue(thisRef: Any, property: KProperty<*>): String = dataStore.get(
            stringPreferencesKey(key),
            defaultValue ?: ""
        )

        override fun setValue(thisRef: Any, property: KProperty<*>, value: String) = dataStore.set(
            stringPreferencesKey(key),
            value
        )
    }

    class IntPreference(
        private val dataStore: DataStore<Preferences>,
        private val key: String,
        private val defaultValue: Int?
    ) : ReadWriteProperty<Any, Int> {
        override fun getValue(thisRef: Any, property: KProperty<*>): Int = dataStore.get(
            intPreferencesKey(key),
            defaultValue ?: 0
        )

        override fun setValue(thisRef: Any, property: KProperty<*>, value: Int) = dataStore.set(
            intPreferencesKey(key),
            value
        )
    }
}
