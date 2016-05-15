package com.mahir.demo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    protected boolean run = true;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        if(run) {
            super.onCreate(savedInstanceState);
            Toast.makeText(this, "On Create", Toast.LENGTH_SHORT).show();
            setContentView(R.layout.activity_main);

        }
        Button destroy = (Button) findViewById(R.id.btn2);
        destroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this,"On Start",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this,"On Resume",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(this,"On Restart",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this,"On Pause",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this,"On Stop",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this,"On Destroy",Toast.LENGTH_SHORT).show();
    }

    public void dialogue(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Dialogue");
        builder.setMessage("Is the activity onPause?");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                run = true;
            }
        });
        // Create the AlertDialog
        builder.show();
        builder.setCancelable(true);
        run = false;
    }
}
