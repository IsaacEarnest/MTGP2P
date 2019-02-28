package com.example.mtg.activities;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.mtg.R;
import com.example.mtg.networking.Utilities;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        String imgURL = getItem(position);




        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        ImageView imageView = convertView.findViewById(R.id.listView_card);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utilities.notifyMessage(mContext, "YOU CLICKED A CARD: " + position);
            }
        });




        return convertView;
    }


}
