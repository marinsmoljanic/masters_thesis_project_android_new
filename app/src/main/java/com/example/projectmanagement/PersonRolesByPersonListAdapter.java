package com.example.projectmanagement;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;

public class PersonRolesByPersonListAdapter extends ArrayAdapter<String> {
    int groupid;
    ArrayList<String> item_list;
    ArrayList<String> desc;
    Context context;

    public PersonRolesByPersonListAdapter(Context context, int vg, int id, ArrayList<String> item_list){
        super(context,vg, id, item_list);
        this.context=context;
        groupid=vg;
        this.item_list=item_list;

    }
    // Hold views of the ListView to improve its scrolling performance
    static class ViewHolder {
        public TextView textview;
        public Button buttonUpdate, buttonDelete;
    }

    @SuppressLint("SetTextI18n")
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = convertView;
        // Inflate the list_item.xml file if convertView is null
        if(rowView==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView= inflater.inflate(groupid, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.textview= (TextView) rowView.findViewById(R.id.txt);
            viewHolder.buttonUpdate= (Button) rowView.findViewById(R.id.btazu);
            viewHolder.buttonDelete= (Button) rowView.findViewById(R.id.bt);
            rowView.setTag(viewHolder);
        }
        // Set text to each TextView of ListView item
        ViewHolder holder = (ViewHolder) rowView.getTag();

        String[] parts = item_list.get(position).split(";");

        holder.textview.setText("Uloga: " + parts[7] + " " + "\n\n" +
                                "Projekt: " + parts[4] + " " + "\n\n" +
                                "Datum dodjele: " + parts[3] + " " + "\n");
        holder.buttonUpdate.setText("Uredi");
        holder.buttonDelete.setText("Obriši");

        int personRoleId = Integer.parseInt(parts[0]);
        holder.buttonDelete.setTag(String.valueOf(personRoleId));
        holder.buttonUpdate.setTag(String.valueOf(personRoleId));

        return rowView;
    }



}
