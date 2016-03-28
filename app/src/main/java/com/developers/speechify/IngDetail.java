package com.developers.speechify;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class IngDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ing_detail);
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        TextView text=(TextView)findViewById(R.id.textView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ingridients");
        Bundle extras=getIntent().getExtras();
        int a=extras.getInt("place");
        ArrayList inglist=DataHolder.getIng();
        text.setText(""+inglist.get(a));


    }

}
