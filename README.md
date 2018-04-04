# FastSave-Android

FastSave is An Android library for fast and easy access to Android Shared preferences.
It allows you to save any type or list in the sharedpreferences and retrieve it in convenient way.

# Installation

<b>Add jitpack.io to your root gradle file (project level)</b>

```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```
<b>Add FastSave-Android to your app level build.gradle dependency</b>

```groovy
dependencies {
    implementation 'com.github.yehiahd:FastSave-Android:1.0.2'
}

```

<b>You have to initialize the FastSave library inside your application class :</b>

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FastSave.init(getApplicationContext());
    }
}

```

### Usage

<b>Saving Values</b>
```java
FastSave.getInstance().saveInt(key,value); // For saving Integer value

FastSave.getInstance().saveFloat(key,value); // For saving Float value

// And so on for other types.

//For Objects and Lists of Objects
FastSave.getInstance().saveObject(key,customObject); // For Saving Custom Object


```

<b>Getting Values</b>
```java

FastSave.getInstance().getInt(key); // For Getting Integer value
FastSave.getInstance().getFloat(key); // For Getting Float value

// And so on for other types.

//For Objects and Lists of Objects
FastSave.getInstance().getObject(key,classType); // For Getting Custom Object

FastSave.getInstance().getObject(key,Person.class); // assuming your custom class called Person

FastSave.getInstance().getObjectList(key,classType); // For Getting Custom Objects List


```

<b>Deleting Values</b>
```java

//Remove element by Key
FastSave.getInstance().deleteValue(key)

//clear all sharedPrefereces
FastSave.getInstance().clearSession();

```
