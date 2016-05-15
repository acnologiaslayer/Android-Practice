package com.fphantom.attendance;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity {

    String password, username, dailywork;
    EditText usr, pass, dailyText;
    private  Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = getApplicationContext();

        usr = (EditText) findViewById(R.id.edit_text_username);
        pass = (EditText) findViewById(R.id.edit_text_password);
        dailyText = (EditText) findViewById(R.id.dailyText);
        TextView date = (TextView) findViewById(R.id.date);
        Button presence = (Button) findViewById(R.id.presence1);
        Button absence = (Button) findViewById(R.id.reasons);
        absence.setVisibility(View.VISIBLE);
        date.setText(getDate());

        username = usr.getText().toString();
        password = pass.getText().toString();
        dailywork = dailyText.getText().toString();

    }

    public String getTime(){
        String mydate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
        return mydate;
    }
    public String getDate(){
        String x= DateFormat.getDateInstance().format(new Date());
        return x;
    }

    public void exit(View view) {
        usr = (EditText) findViewById(R.id.edit_text_username);
        pass = (EditText) findViewById(R.id.edit_text_password);
        dailyText = (EditText) findViewById(R.id.dailyText);


        username = usr.getText().toString();
        password = pass.getText().toString();
        dailywork = dailyText.getText().toString();

        String prams = "http://fphantom.com/attendance/android_checkout.php?textfield1="+username+"&textfield2="+password;
        JSONTask jt1 = new JSONTask(mContext);
        jt1.execute(prams);

    }

    public void reason(View v){

        Intent i = new Intent(getApplicationContext(),Reasons.class);
        startActivity(i);

    }

    public void check_in(View v){
        /*Wifi_Tracker wifi = new Wifi_Tracker(mContext);
        String MAC = wifi.getMACAddress();
        MAC = MAC.substring(0,17);
        //Toast.makeText(getApplicationContext(), MAC, Toast.LENGTH_LONG).show();

        if(!MAC.equals("e4:8d:8c:15:e5:9b")) {
            Toast.makeText(getApplicationContext(), "You are not at Fphantom Office!!!", Toast.LENGTH_LONG).show();
        }
        else{
            /*new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Fphantom Office")
                    .setMessage("You are at Fphantom Office.. Your attendance has been recorded!!")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                        }
                    })

                    .show();*/
        usr = (EditText) findViewById(R.id.edit_text_username);
        pass = (EditText) findViewById(R.id.edit_text_password);
        dailyText = (EditText) findViewById(R.id.dailyText);


        username = usr.getText().toString();
        password = pass.getText().toString();
        dailywork = dailyText.getText().toString();

            String params = "http://fphantom.com/attendance/android_mod.php?textfield1="+username+"&textfield2="+password;
            JSONTask jt2 = new JSONTask(mContext);
            jt2.execute(params);
        //}
    }

    @Override
    protected void onPause() {
        super.onPause();

    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putString("username_pause", username);
        savedInstanceState.putString("password_pause", password);
        savedInstanceState.putString("daily_pause", dailywork);
        // etc.
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore UI state from the savedInstanceState.
        // This bundle has also been passed to onCreate.

        username = savedInstanceState.getString("username_pause");
        password = savedInstanceState.getString("password_pause");
        dailywork = savedInstanceState.getString("daily_pause");

        usr.setText(username);
        pass.setText(password);
        dailyText.setText(dailywork);
    }


}
