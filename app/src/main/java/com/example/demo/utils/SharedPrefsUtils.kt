package com.example.demo.utils

import android.content.Context
import android.content.SharedPreferences
import android.text.TextUtils
import com.example.demo.MyApplication
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken


class SharedPrefsUtils {

    companion object {

        val mode = Context.MODE_PRIVATE
        val sharedprefernce_key = "Mypref"
        private val sharedPref: SharedPreferences =
            MyApplication.context.applicationContext
                .getSharedPreferences(sharedprefernce_key, mode)


        fun getStringPreference(key: String): String {
            var value: String = ""
            val preferences = sharedPref
            value = preferences.getString(key, "").toString()
            return value
        }

        fun setStringPreference(key: String, value: String): Boolean {
            if (!TextUtils.isEmpty(key)) {
                val editor = sharedPref.edit()
                editor.putString(key, value)
                return editor.commit()
            }
            return false
        }

        fun getFloatPreference(key: String, defaultValue: Float): Float {
            var value = defaultValue
            value = sharedPref.getFloat(key, defaultValue)
            return value
        }


        fun setFloatPreference(key: String, value: Float): Boolean {
            val editor = sharedPref.edit()
            editor.putFloat(key, value)
            return editor.commit()

        }

        fun getLongPreference(key: String, defaultValue: Long): Long {
            var value = defaultValue
            val preferences = sharedPref
            value = preferences.getLong(key, defaultValue)
            return value
        }

        fun setLongPreference(key: String, value: Long): Boolean {
            val editor = sharedPref.edit()
            editor.putLong(key, value)
            return editor.commit()
        }

        fun getIntegerPreference(key: String, defaultValue: Int): Int {
            var value: Int = defaultValue
            value = sharedPref.getInt(key, defaultValue.toInt()).toInt()
            return value
        }

        fun setIntegerPreference(key: String, value: Int): Boolean {
            val editor = sharedPref.edit()
            editor.putInt(key, value)
            return editor.commit()

        }


        fun getBooleanPreference(key: String, defaultValue: Boolean): Boolean {
            var value = defaultValue
            value = sharedPref.getBoolean(key, defaultValue)
            return value
        }


        fun setBooleanPreference(key: String, value: Boolean): Boolean {

            val editor = sharedPref.edit()
            editor.putBoolean(key, value)
            return editor.commit()
        }


        fun <T> getObjectList(key: String, cls: Class<T>): MutableList<T> {
            val list = ArrayList<T>()
            try {
                val gson = Gson()
                val json = sharedPref.getString(key, "")
                val arry = JsonParser().parse(json).asJsonArray
                for (jsonElement in arry) {
                    list.add(gson.fromJson(jsonElement, cls))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }

            return list
        }

        fun <T> getListPreference(key: String): MutableList<T> {

            val gson = Gson()
            val json = sharedPref.getString(key, "")

            var yourArrayList = ArrayList<T>()

            val typeMyType = object : TypeToken<MutableList<T>>() {

            }.type

            yourArrayList = gson.fromJson(json, typeMyType)

            return yourArrayList
        }

        fun <T> setListPreference(key: String, list: List<T>) {
            val prefsEditor = sharedPref.edit()
            val gson = Gson()
            val json = gson.toJson(list)
            prefsEditor.putString(key, json)
            prefsEditor.apply()
        }


//        fun remove(email: String, password: String) {
//            val prefsEditor = sharedPref.edit()
//            prefsEditor.clear().apply()
//        }
        /**
         * Clear single key value
         * @param prefKey
         */
        fun remove(prefKey: String): Boolean {
            return sharedPref.edit().remove(prefKey).commit()
        }

        /**
         * Clear single key value
         * @param prefKey
         */
        fun remove() {
            val prefsMap: Map<String, *> = sharedPref.all
            for ((key, value) in prefsMap) {
                when (key) {
                    KeysUtils.keyEmail -> { }
                    KeysUtils.keyPassword -> { }
                    else -> {
                        sharedPref.edit().remove(key).apply()
                    }
                }
            }

            fun setModelPreferences(key: String, value: Any) {
                val prefsEditor = sharedPref.edit()
                val gson = Gson()
                val json = gson.toJson(value)
                prefsEditor.putString(key, json)
                prefsEditor.apply()
            }

            fun <T> getModelPreferences(key: String, objectClass: Class<T>): Any? {
                val gson = Gson()
                val json = sharedPref.getString(key, "")
                return if (!TextUtils.isEmpty(json)) {
                    gson.fromJson(json, objectClass)
                } else null
            }

        }
    }
}