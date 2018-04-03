package com.appizona.yehiahd.fastsave;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.common.reflect.TypeParameter;
import com.google.gson.Gson;

import java.util.List;

public class FastSave {

    private static FastSave instance;
    private static SharedPreferences mSharedPreferences;

    private FastSave() {
    }

    public static void init(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static FastSave getInstance() {
        validateInitialization();
        if (instance == null) {
            synchronized (FastSave.class) {
                if (instance == null) {
                    instance = new FastSave();
                }
            }
        }
        return instance;
    }

    public void saveInt(String key, int value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getInt(String key) {
        return mSharedPreferences.getInt(key, 0);
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }


    public void saveFloat(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key) {
        return mSharedPreferences.getFloat(key, 0.0f);
    }


    public void saveLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key) {
        return mSharedPreferences.getLong(key, 0);
    }


    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        return mSharedPreferences.getString(key, "");
    }

    public <T> void saveObject(String key, T object) {
        String objectString = new Gson().toJson(object);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, objectString);
        editor.apply();
    }

    public <T> T getObject(String key, Class<T> classType) {
        String objectString = mSharedPreferences.getString(key, null);
        if (objectString != null) {
            return new Gson().fromJson(objectString, classType);
        }
        return null;
    }


    public <T> void saveObjectList(String key, List<T> objectList) {
        String objectString = new Gson().toJson(objectList);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, objectString);
        editor.apply();
    }

    public <T> List<T> getObjectList(String key, Class<T> classType) {

        String objectString = mSharedPreferences.getString(key, null);
        if (objectString != null) {
            return new Gson().fromJson(objectString, new com.google.common.reflect.TypeToken<List<T>>() {
            }
                    .where(new TypeParameter<T>() {
                    }, classType)
                    .getType());
        }
        return null;
    }

    public void clearSession() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }


    private static void validateInitialization() {
        if (mSharedPreferences == null)
            throw new FastException("FastSave Library must be initialized inside your application class by calling FastSave.init(getApplicationContext)");
    }

}
