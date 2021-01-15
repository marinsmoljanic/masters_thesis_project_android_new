package com.example.projectmanagement;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PersonInfoList {
    DbHandler readDb;
    private Context context;
    ListView listView;
    View view;
    ArrayList<String> listaOsoba;

    public PersonInfoList(Context context, View view){
        this.context = context;
        this.view = view;
    }

    public ArrayList<String> getList(){
        readDb = new DbHandler(context);
        Cursor cursor = readDb.readAllFromTableOsoba();
        listView = (ListView) view.findViewById(R.id.persons_list);
        listaOsoba = new ArrayList<String>();
        Log.d("myTag", "This is my message");

        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String prezime = cursor.getString(1);
                String ime = cursor.getString(2);
                String OIB = cursor.getString(3);
                listaOsoba.add(id + ";"  + prezime + ";" + ime + ";" + OIB);
                // map.put(id, new HashMap(){{put("prezime", prezime);}});
                // map.get(id).put("ime", ime);
                // map.get(id).put("OIB", OIB);
            }
        } finally {
            cursor.close();
        }

        return listaOsoba;
    }
}