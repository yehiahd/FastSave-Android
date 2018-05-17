# FastSave-Android

FastSave is An Android library for fast and easy access to Android Shared preferences.
It allows you to save any type or list in the sharedpreferences and retrieve it in convenient way.

# Installation


* **Gradle**

	Add jitpack.io to your root gradle file (project level) :
	```gradle
  allprojects {
  		repositories {
  			...
  			maven { url 'https://jitpack.io' }
  		}
  	}
	```

	Add the dependency in your app build.gradle
	```gradle
  dependencies {
      implementation 'com.github.yehiahd:FastSave-Android:1.0.4'
  }
	```

* **Maven**

	Add the JitPack repository to your build file
	```gradle
	<repositories>
    		<repository>
    		    <id>jitpack.io</id>
    		    <url>https://jitpack.io</url>
    		</repository>
    	</repositories>
	```

	Add FastSave-Android to your app level build.gradle dependency

	```gradle
  <dependency>
  	    <groupId>com.github.yehiahd</groupId>
  	    <artifactId>FastSave-Android</artifactId>
  	    <version>1.0.4</version>
  	</dependency>
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

FastSave.getInstance().saveObjectList(key,listOfCustomObjects); // For Saving Custom Objects List

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
