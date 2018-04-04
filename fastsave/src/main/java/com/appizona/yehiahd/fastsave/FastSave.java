package com.appizona.yehiahd.fastsave;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.common.reflect.TypeParameter;
import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

public class FastSave {

    private static FastSave instance;
    private static SharedPreferences mSharedPreferences;

    private FastSave() {
    }

    public static void init(Context context) {
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static FastSave getInstance() {
        if (instance == null) {
            validateInitialization();
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
        if (isValidKey(key)) {
            return mSharedPreferences.getInt(key, 0);
        }
        return 0;
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean getBoolean(String key) {
        if (isValidKey(key)) {
            return mSharedPreferences.getBoolean(key, false);
        } else {
            return false;
        }
    }


    public void saveFloat(String key, float value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putFloat(key, value);
        editor.apply();
    }

    public float getFloat(String key) {
        if (isValidKey(key)) {
            return mSharedPreferences.getFloat(key, 0.0f);
        }
        return 0.0f;
    }


    public void saveLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long getLong(String key) {
        if (isValidKey(key)) {
            return mSharedPreferences.getLong(key, 0);
        }
        return 0;
    }


    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {
        if (isValidKey(key)) {
            return mSharedPreferences.getString(key, null);
        }
        return null;
    }

    public <T> void saveObject(String key, T object) {
        String objectString = new Gson().toJson(object);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, objectString);
        editor.apply();
    }

    public <T> T getObject(String key, Class<T> classType) {
        if (isValidKey(key)) {
            String objectString = mSharedPreferences.getString(key, null);
            if (objectString != null) {
                return new Gson().fromJson(objectString, classType);
            }
        }
        return null;
    }


    public <T> void saveObjectsList(String key, List<T> objectList) {
        String objectString = new Gson().toJson(objectList);
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, objectString);
        editor.apply();
    }

    public <T> List<T> getObjectsList(String key, Class<T> classType) {
        if (isValidKey(key)) {
            String objectString = mSharedPreferences.getString(key, null);
            if (objectString != null) {
                return new Gson().fromJson(objectString, new com.google.common.reflect.TypeToken<List<T>>() {
                }
                        .where(new TypeParameter<T>() {
                        }, classType)
                        .getType());
            }
        }

        return null;
    }

    public void clearSession() {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public boolean deleteValue(String key) {
        if (isValidKey(key)) {
            SharedPreferences.Editor editor = mSharedPreferences.edit();
            editor.remove(key);
            editor.apply();
            return true;
        }

        return false;
    }


    private static void validateInitialization() {
        if (mSharedPreferences == null)
            throw new FastException("FastSave Library must be initialized inside your application class by calling FastSave.init(getApplicationContext)");
    }

    private boolean isValidKey(String key) {
        Map<String, ?> map = mSharedPreferences.getAll();
        if (map.containsKey(key)) {
            return true;
        } else {
            Log.e("FastSave", "No element founded in sharedPrefs with the key " + key);
            return false;
        }
    }

}
