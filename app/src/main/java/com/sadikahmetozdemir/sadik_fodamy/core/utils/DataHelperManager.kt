package com.sadikahmetozdemir.sadik_fodamy.core.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataHelperManager @Inject constructor(private val context: Context) {
    suspend fun saveToken(token:String){
        context.dataStore.edit {
            it[TOKEN]=token
        }
    }
    suspend fun removeToken(){
        context.dataStore.edit {
            it.remove(TOKEN)
        }
    }
    suspend fun getToken():String=context.dataStore.data.map {
        it[TOKEN]?:""
    }.first()
    suspend fun firstAttach(){
        context.dataStore.edit {
            it[ATTACH]=false
        }
    }

    suspend fun isFirstAttach():Boolean=context.dataStore.data.map {
        it[ATTACH]?:true
    }.first()
    suspend fun saveID(id:Int){
        context.dataStore.edit {
            it[ID]=id
        }
    }
    suspend fun isLogin():Boolean=getToken().isNotBlank()






    companion object{
        private val ID= intPreferencesKey("id")
        private val ATTACH= booleanPreferencesKey("attach")
        private val TOKEN= stringPreferencesKey("token")
        val Context.dataStore:DataStore<Preferences> by preferencesDataStore("Data")
    }
}