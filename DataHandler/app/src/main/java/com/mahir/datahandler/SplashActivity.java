package com.mahir.datahandler;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler(){
            String message = "Wait";
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                message += (String) msg.obj;
                ((TextView)findViewById(R.id.texty)).setText(message);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i=0;i<4;i++) {
                        Thread.sleep(1000);
                        Message m = handler.obtainMessage();
                        m.obj=".";
                        handler.sendMessage(m);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }).start();
    }
}
