package com.example.mtg.activities;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class CustomListViewAdapter extends ArrayAdapter<String> {

    private static final String TAG = "CUSTOMLISTVIEWADAPTER";

    private Context mContext;
    private int mResource;

    public CustomListViewAdapter(Context context, int resource, List<String> objects) {

        super(context, resource, objects);
        this.mContext = context;
        this.mResource =resource;

    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String imgURL = getItem(position);




        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);






        return convertView;
    }


}
