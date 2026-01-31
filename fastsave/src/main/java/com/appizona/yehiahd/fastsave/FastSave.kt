package com.appizona.yehiahd.fastsave

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class FastSave private constructor() {

    companion object {
        @Volatile
        private var instance: FastSave? = null
        private var mSharedPreferences: SharedPreferences? = null

        @JvmStatic
        fun init(context: Context) {
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        }

        @JvmStatic
        fun init(sharedPreferences: SharedPreferences) {
            mSharedPreferences = sharedPreferences
        }

        @JvmStatic
        fun getInstance(): FastSave {
            if (instance == null) {
                validateInitialization()
                synchronized(FastSave::class.java) {
                    if (instance == null) {
                        instance = FastSave()
                    }
                }
            }
            return instance!!
        }

        private fun validateInitialization() {
            if (mSharedPreferences == null) {
                throw FastException("FastSave Library must be initialized inside your application class by calling FastSave.init(getApplicationContext)")
            }
        }
    }

    fun saveInt(key: String, value: Int) {
        mSharedPreferences!!.edit().putInt(key, value).apply()
    }

    fun getInt(key: String, defaultValue: Int): Int {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getInt(key, defaultValue)
        } else defaultValue
    }

    fun saveBoolean(key: String, value: Boolean) {
        mSharedPreferences!!.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getBoolean(key, defaultValue)
        } else defaultValue
    }

    fun saveFloat(key: String, value: Float) {
        mSharedPreferences!!.edit().putFloat(key, value).apply()
    }

    fun getFloat(key: String, defaultValue: Float): Float {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getFloat(key, defaultValue)
        } else defaultValue
    }

    fun saveLong(key: String, value: Long) {
        mSharedPreferences!!.edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getLong(key, defaultValue)
        } else defaultValue
    }

    fun saveString(key: String, value: String) {
        mSharedPreferences!!.edit().putString(key, value).apply()
    }

    fun getString(key: String, defaultValue: String): String {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.getString(key, defaultValue) ?: defaultValue
        } else defaultValue
    }

    fun <T> saveObject(key: String, obj: T) {
        val objectString = Gson().toJson(obj)
        mSharedPreferences!!.edit().putString(key, objectString).apply()
    }

    fun <T> getObject(key: String, classType: Class<T>): T? {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)
            if (objectString != null) {
                return Gson().fromJson(objectString, classType)
            }
        }
        return null
    }

    fun <T> saveObjectsList(key: String, objectList: List<T>) {
        val objectString = Gson().toJson(objectList)
        mSharedPreferences!!.edit().putString(key, objectString).apply()
    }

    fun <T> getObjectsList(key: String, classType: Class<T>): List<T>? {
        if (isKeyExists(key)) {
            val objectString = mSharedPreferences!!.getString(key, null)
            if (objectString != null) {
                val list: ArrayList<T> = Gson().fromJson(objectString, object : TypeToken<List<T>>() {}.type)
                val finalList = ArrayList<T>()
                for (i in list.indices) {
                    val s = list[i].toString()
                    finalList.add(Gson().fromJson(s, classType))
                }
                return finalList
            }
        }
        return null
    }

    fun clearSession() {
        mSharedPreferences!!.edit().clear().apply()
    }

    fun deleteValue(key: String): Boolean {
        return if (isKeyExists(key)) {
            mSharedPreferences!!.edit().remove(key).apply()
            true
        } else {
            false
        }
    }

    fun isKeyExists(key: String): Boolean {
        val map = mSharedPreferences!!.all
        return if (map.containsKey(key)) {
            true
        } else {
            Log.e("FastSave", "No element founded in sharedPrefs with the key $key")
            false
        }
    }

    fun removeAllExcept(vararg keys: String): Boolean {
        var isMatch: Boolean
        val editor = mSharedPreferences!!.edit()
        for (key in mSharedPreferences!!.all.keys) {
            isMatch = false
            for (k in keys) {
                if (key == k) {
                    isMatch = true
                }
            }
            if (!isMatch) {
                editor.remove(key)
            }
        }
        editor.apply()
        return true
    }
}
