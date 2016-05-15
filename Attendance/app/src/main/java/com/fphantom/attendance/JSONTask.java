package com.fphantom.attendance;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by deep on 4/27/16.
 */
public class JSONTask extends AsyncTask<String, Void, String> {


    private  Context mContext;
    ProgressDialog progressDialog;

    public JSONTask(Context v){

        mContext=v;

    }

    @Override
    protected void onPreExecute() {

        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... uRls) {
        try {

            return downloadContent(uRls[0]);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String aBoolean) {
        super.onPostExecute(aBoolean);



        /* OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput("save_CNG.txt", Context.MODE_PRIVATE));
         outputStreamWriter.write(page);
         outputStreamWriter.close();*/

        Toast.makeText(mContext.getApplicationContext(),aBoolean, Toast.LENGTH_LONG).show();


    }

    private String downloadContent(String myurl) throws IOException {
        InputStream is = null;
        int length = 500;

        String [] pram = myurl.split("=");
        String pass = pram[2];
        String uname = pram[1].split("&")[0];

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("textfield1", uname)
                    .appendQueryParameter("textfield2", pass);
            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

            int response = conn.getResponseCode();
            is = conn.getInputStream();

            // Convert the InputStream into a string
            String contentAsString = convertInputStreamToString(is, length);
            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }
}