package com.appizona.yehiahd.fastsaveexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.appizona.yehiahd.fastsave.FastSave;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.edit_text)
    EditText editText;
    @BindView(R.id.save_int)
    Button saveInt;
    @BindView(R.id.save_float)
    Button saveFloat;
    @BindView(R.id.save_long)
    Button saveLong;
    @BindView(R.id.save_string)
    Button saveString;
    @BindView(R.id.save_boolean)
    Button saveBoolean;
    @BindView(R.id.get_int)
    Button getInt;
    @BindView(R.id.get_float)
    Button getFloat;
    @BindView(R.id.get_long)
    Button getLong;
    @BindView(R.id.get_string)
    Button getString;
    @BindView(R.id.get_boolean)
    Button getBoolean;
    @BindView(R.id.save_object)
    Button saveObject;
    @BindView(R.id.get_object)
    Button getObject;
    @BindView(R.id.save_objects_list)
    Button saveObjectsList;
    @BindView(R.id.get_objects_list)
    Button getObjectsList;
    @BindView(R.id.value_TV)
    TextView valueTV;
    @BindView(R.id.clear_all)
    Button clearAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        saveInt.setOnClickListener(this);
        saveBoolean.setOnClickListener(this);
        saveLong.setOnClickListener(this);
        saveFloat.setOnClickListener(this);
        saveString.setOnClickListener(this);
        saveObject.setOnClickListener(this);
        saveObjectsList.setOnClickListener(this);
        clearAll.setOnClickListener(this);

        getObject.setOnClickListener(this);
        getObjectsList.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save_int:
                FastSave.getInstance().saveInt("int_key", 22);
                break;

            case R.id.save_boolean:
                FastSave.getInstance().saveBoolean("boolean_key", true);
                break;

            case R.id.save_long:
                FastSave.getInstance().saveLong("long_key", 2222);

                break;

            case R.id.save_float:
                FastSave.getInstance().saveFloat("float_key", 22.0f);

                break;

            case R.id.save_string:
                FastSave.getInstance().saveString("string_key", "My Age is 22");

                break;

            case R.id.save_object:
                Person person = new Person()
                        .setId(22)
                        .setName("Yehia")
                        .setEmployee(true);
                FastSave.getInstance().saveObject("object_key", person);
                break;

            case R.id.save_objects_list:
                List<Person> list = getListOfObjects();
                FastSave.getInstance().saveObjectsList("objects_list_key", list);

                break;

            case R.id.clear_all:
                FastSave.getInstance().clearSession();
                break;

            case R.id.get_object:
                Person p = FastSave.getInstance().getObject("object_key", Person.class);
                valueTV.setText(p.getName());
                break;

            case R.id.get_objects_list:
                List<Person> pl = FastSave.getInstance().getObjectsList("objects_list_key", Person.class);
                valueTV.setText(pl.get(1).getName());
                break;


        }
    }

    private List<Person> getListOfObjects() {
        List<Person> list = new ArrayList<>();
        Person person1 = new Person()
                .setId(22)
                .setName("Yehia")
                .setEmployee(true);

        Person person2 = new Person()
                .setId(44)
                .setName("Mohamed")
                .setEmployee(false);

        Person person3 = new Person()
                .setId(30)
                .setName("Android")
                .setEmployee(true);

        Person person4 = new Person()
                .setId(33)
                .setName("Google")
                .setEmployee(true);

        list.add(person1);
        list.add(person2);
        list.add(person3);
        list.add(person4);

        return list;
    }
}
