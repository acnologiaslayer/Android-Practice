package com.mahir.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<HashMap<String,Double>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<HashMap<String, Double>>();
        String url = "http://api.fixer.io/latest";

        RequestQueue queue = Volley.newRequestQueue(this);
        JsonObjectRequest objReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject=response.getJSONObject("rates");
                    Iterator<String> iter = jsonObject.keys();
                    while (iter.hasNext()){
                        String key = iter.next();
                        Double value = jsonObject.getDouble(key);
                        HashMap<String,Double> map = new HashMap<String, Double>();
                        map.put(key,value);
                        list.add(map);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}
