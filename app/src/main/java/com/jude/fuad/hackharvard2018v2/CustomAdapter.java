package com.jude.fuad.hackharvard2018v2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;


import java.util.ArrayList;
import java.util.List;


public class CustomAdapter extends ArrayAdapter<DataField> {

    private Context mContext;
    private List<DataField> moviesList = new ArrayList<DataField>();

    public CustomAdapter(@NonNull Context context, ArrayList<DataField> list) {
        super(context, 0 , list);
        mContext = context;
        moviesList = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null)
            listItem = LayoutInflater.from(mContext).inflate(R.layout.listitems,parent,false);

        DataField currentField = moviesList.get(position);
        EditText val = listItem.findViewById(R.id.valEdit);
        val.setHint("Username");

        EditText type = listItem.findViewById(R.id.typeEdit);
        type.setHint("Service");
        return listItem;
    }
}