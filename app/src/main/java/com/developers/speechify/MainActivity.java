package com.developers.speechify;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ProgressDialog pDialog;
    ArrayList<String> namelist =new ArrayList<String>();
    ArrayList<String> ingredients=new ArrayList<String>();
    HashMap<Integer,ArrayList> in=new HashMap<Integer,ArrayList>();
    ArrayList<String> inn=new ArrayList<String>();
    private Intent c;
    CustomListAdapter custom=new CustomListAdapter(this,namelist);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pDialog=new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        c=new Intent(this,IngDetail.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Speechify");
        String url="http://www.speechify.in/internship/android_task.php";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject root= new JSONObject(response);
                            JSONArray arr=root.optJSONArray("recipe_data");
                            for(int i=0;i<arr.length();i++)
                            {   ingredients.clear();
                                JSONObject ob=arr.getJSONObject(i);
                                String name =ob.getString("name");
                                namelist.add(name);
                                JSONArray ingredientarr=ob.getJSONArray("ingredient_data");
                                for(int j=0;j<ingredientarr.length();j++){
                                    JSONObject ingob=ingredientarr.getJSONObject(j);
                                    String ingname=ingob.getString("ingredient_name");
                                    ingredients.add(ingname);
                                }
                                in.put(i, ingredients);
                                System.out.println(" " + i + " " + in.get(i));
                                inn.add(in.get(i).toString());

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        DataHolder.setIng(inn);
                        ListView l=(ListView)findViewById(R.id.listView);
                        l.setAdapter(custom);
                        pDialog.hide();
                        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                c.putExtra("place",position);
                                startActivity(c);
                            }
                        });

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                // Error handling
                System.out.println("Something went wrong!");
                error.printStackTrace();

            }
        });



// Add the request to the queue
        Volley.newRequestQueue(this).add(stringRequest);

    }
}
