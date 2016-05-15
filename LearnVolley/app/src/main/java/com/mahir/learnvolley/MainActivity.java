package com.mahir.learnvolley;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends ListActivity {

    ArrayList<HashMap<String,String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<HashMap<String, String>>();
        String url="http://www.androidbegin.com/tutorial/jsonparsetutorial.txt";
        RequestQueue rq = Volley.newRequestQueue(this);
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("Response >>",response.toString());
                try {
                    JSONArray ja = response.getJSONArray("worldpopulation");
                    for (int i=0;i<ja.length();i++){
                        JSONObject obj = ja.getJSONObject(i);
                        String rank = obj.getString("rank");
                        String country = obj.getString("country");
                        String population = obj.getString("population");
                        HashMap<String,String> map = new HashMap<String, String>();
                        map.put("rank",rank);
                        map.put("country",country);
                        map.put("population",population);
                        list.add(map);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ListAdapter adapter = new SimpleAdapter(MainActivity.this,list,R.layout.list_item,new String[]{"rank","country","population"},new int[]{R.id.textView,R.id.textView2,R.id.textView3});
                setListAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        rq.add(jor);
    }
}
