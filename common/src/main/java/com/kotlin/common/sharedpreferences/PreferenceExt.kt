package com.kotlin.common.sharedpreferences

import android.content.Context
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class Preference<T>(val context: Context, val name:String,val default:T,val preName:String="default")
    :ReadWriteProperty<Any?,T>{
    val prefs by lazy {
        context.getSharedPreferences(preName, Context.MODE_PRIVATE)
    }
    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
          return findPreference(findPropertyName(property))

    }

    private fun findPropertyName(property:KProperty<*>)= if(name.isEmpty()) property.name else name

    private fun findPreference(key:String): T {
       return when (default) {
            is Int -> prefs.getInt(key, default)
            is Boolean -> prefs.getBoolean(key, default)
            is Long -> prefs.getLong(key, default)
            is String -> prefs.getString(key,default)
            else -> throw IllegalArgumentException("Unsupported type!")
        } as T
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        putPreferty(findPropertyName(property),value)
    }

    private fun putPreferty(key:String,value: T) {
        with(prefs.edit()) {
            when (value) {
                is Int -> putInt(key, value)
                is Boolean -> putBoolean(key, value)
                is Long -> putLong(key, value)
                is String->putString(key,value)
                else -> throw IllegalArgumentException("Unsupported type!")
            }
        }.apply()
    }

}