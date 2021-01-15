package com.example.projectmanagement;


import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class RoleInfoList {
    DbHandler readDb;
    private Context context;
    ListView listView;
    View view;
    ArrayList<String> listaUloga;


    public RoleInfoList(Context context, View view){
        this.context = context;
        this.view = view;
    }

    public ArrayList<String> getList(){
        readDb = new DbHandler(context);
        Cursor cursor = readDb.readAllFromTableUloga();
        listView = (ListView) view.findViewById(R.id.roles_list);
        listaUloga = new ArrayList<String>();

        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String idUloge = cursor.getString(0);
                String nazUloge = cursor.getString(1);
                listaUloga.add(idUloge + ";"  + nazUloge);
            }
        } finally {
            cursor.close();
        }

        return listaUloga;
    }
}
