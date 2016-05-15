package com.mahir.retieveresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private final int REQUEST_CODE=12121;
    EditText firstName,middleName,lastName,finalResult;
    String fname,mname,lname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName=(EditText)findViewById(R.id.editText);
        middleName=(EditText)findViewById(R.id.editText2);
        lastName=(EditText)findViewById(R.id.editText3);
    }

    public void getResult(View v){
        fname = firstName.getText().toString();
        mname = middleName.getText().toString();
        lname = lastName.getText().toString();

        Intent i = new Intent(MainActivity.this,HandlerActivity.class);
        i.putExtra("first",fname);
        i.putExtra("middle",mname);
        i.putExtra("last",lname);
        startActivityForResult(i,REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==REQUEST_CODE && resultCode==RESULT_OK){
            finalResult=(EditText)findViewById(R.id.editText4);
            finalResult.setText(data.getStringExtra("result"));
        }
    }
}
