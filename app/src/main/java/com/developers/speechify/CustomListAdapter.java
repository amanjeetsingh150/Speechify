package com.developers.speechify;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Amanjeet Singh on 28-Mar-16.
 */
public class CustomListAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private Activity activity;
    private ArrayList<String> p=new ArrayList<String>();
    public CustomListAdapter(Activity activity,ArrayList p){
        this.activity=activity;
        this.p=p;
    }
    @Override
    public int getCount() {
        return p.size();
    }

    @Override
    public Object getItem(int position) {
        return p.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView=inflater.inflate(R.layout.list_row,null);
            String h= p.get(position);
            TextView t1=(TextView)convertView.findViewById(R.id.tt);
            t1.setText(h);
        return convertView;
    }
}
