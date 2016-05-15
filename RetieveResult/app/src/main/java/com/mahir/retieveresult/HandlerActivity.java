package com.mahir.retieveresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        Intent i = getIntent();
        String fname = i.getStringExtra("first");
        String mname = i.getStringExtra("middle");
        String lname = i.getStringExtra("last");
        String finale = fname+" "+mname+" "+lname;
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("result",finale);
        setResult(RESULT_OK,intent);
       finish();
    }
}
