package com.mahir.databaseresponse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name,class_name,email;
    DatabaseManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.editText);
        class_name = (EditText) findViewById(R.id.editText2);
        email = (EditText) findViewById(R.id.editText3);
        manager = new DatabaseManager(this);
    }

    public void save(View view){
        Student_Detail sd = new Student_Detail();
        sd.setName(name.getText().toString());
        sd.setClass_name(class_name.getText().toString());
        sd.setEmail(email.getText().toString());
        if(manager.inset(sd)>0){
            Toast.makeText(getApplicationContext(),"Success!!",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Failed!!",Toast.LENGTH_SHORT).show();
        }
    }
}
