package com.appizona.yehiahd.fastsaveexample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.appizona.yehiahd.fastsave.FastSave

class MainActivity : AppCompatActivity(), View.OnClickListener {

    @BindView(R.id.edit_text)
    lateinit var editText: EditText

    @BindView(R.id.save_int)
    lateinit var saveInt: Button

    @BindView(R.id.save_float)
    lateinit var saveFloat: Button

    @BindView(R.id.save_long)
    lateinit var saveLong: Button

    @BindView(R.id.save_string)
    lateinit var saveString: Button

    @BindView(R.id.save_boolean)
    lateinit var saveBoolean: Button

    @BindView(R.id.get_int)
    lateinit var getInt: Button

    @BindView(R.id.get_float)
    lateinit var getFloat: Button

    @BindView(R.id.get_long)
    lateinit var getLong: Button

    @BindView(R.id.get_string)
    lateinit var getString: Button

    @BindView(R.id.get_boolean)
    lateinit var getBoolean: Button

    @BindView(R.id.save_object)
    lateinit var saveObject: Button

    @BindView(R.id.get_object)
    lateinit var getObject: Button

    @BindView(R.id.save_objects_list)
    lateinit var saveObjectsList: Button

    @BindView(R.id.get_objects_list)
    lateinit var getObjectsList: Button

    @BindView(R.id.value_TV)
    lateinit var valueTV: TextView

    @BindView(R.id.clear_all)
    lateinit var clearAll: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ButterKnife.bind(this)

        saveInt.setOnClickListener(this)
        saveBoolean.setOnClickListener(this)
        saveLong.setOnClickListener(this)
        saveFloat.setOnClickListener(this)
        saveString.setOnClickListener(this)
        saveObject.setOnClickListener(this)
        saveObjectsList.setOnClickListener(this)
        clearAll.setOnClickListener(this)

        getObject.setOnClickListener(this)
        getObjectsList.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.save_int -> FastSave.getInstance().saveInt("int_key", 22)

            R.id.save_boolean -> FastSave.getInstance().saveBoolean("boolean_key", true)

            R.id.save_long -> FastSave.getInstance().saveLong("long_key", 2222)

            R.id.save_float -> FastSave.getInstance().saveFloat("float_key", 22.0f)

            R.id.save_string -> FastSave.getInstance().saveString("string_key", "My Age is 22")

            R.id.save_object -> {
                val person = Person()
                    .setId(22)
                    .setName("Yehia")
                    .setEmployee(true)
                FastSave.getInstance().saveObject("object_key", person)
            }

            R.id.save_objects_list -> {
                val list = getListOfObjects()
                FastSave.getInstance().saveObjectsList("objects_list_key", list)
            }

            R.id.clear_all -> FastSave.getInstance().clearSession()

            R.id.get_object -> {
                val p = FastSave.getInstance().getObject("object_key", Person::class.java)
                valueTV.text = p?.name
            }

            R.id.get_objects_list -> {
                val pl = FastSave.getInstance().getObjectsList("objects_list_key", Person::class.java)
                valueTV.text = pl?.get(1)?.name
            }
        }
    }

    private fun getListOfObjects(): List<Person> {
        val list = ArrayList<Person>()
        val person1 = Person()
            .setId(22)
            .setName("Yehia")
            .setEmployee(true)

        val person2 = Person()
            .setId(44)
            .setName("Mohamed")
            .setEmployee(false)

        val person3 = Person()
            .setId(30)
            .setName("Android")
            .setEmployee(true)

        val person4 = Person()
            .setId(33)
            .setName("Google")
            .setEmployee(true)

        list.add(person1)
        list.add(person2)
        list.add(person3)
        list.add(person4)

        return list
    }
}
