package com.example.nevo.listview;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private Context context;
    private List<Item> objects;

    public ItemAdapter(@NonNull Context context, int resource, int textViewResourceId, List<Item> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View v = inflater.inflate(R.layout.custom_layout,null,false);

        //Item item = new Item("hi","5.0",pic);
        ((TextView)v.findViewById(R.id.tvName)).setText(objects.get(position).getName());
        ((TextView)v.findViewById(R.id.tvSize)).setText(objects.get(position).getSize());
        ((ImageView)v.findViewById(R.id.img)).setImageBitmap(objects.get(position).getImage());
        return v;
    }
}
