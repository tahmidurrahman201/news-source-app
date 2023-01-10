package com.example.newssources;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ModelOfNews> list;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    Connector connector;
    String apiUrl="https://api.npoint.io/1284daa171d31158360b";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RequestQueue requestQueue= Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, apiUrl, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    list=new ArrayList<>();
                    JSONArray jsonArray=response.getJSONArray("sources");
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        list.add(new ModelOfNews(jsonObject.getString("name"),jsonObject.getString("description"),jsonObject.getString("category"),jsonObject.getString("url")));
                    }
                    setRecycler();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);

    }

    private void setRecycler() {
        recyclerView=findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        connector=new Connector(list,this);
        recyclerView.setAdapter(connector);
        connector.notifyDataSetChanged();
    }


}