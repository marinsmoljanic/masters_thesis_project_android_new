package com.example.projectmanagement;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;


public class ProjectInfoList {
    DbHandler readDb;
    private Context context;
    ListView listView;
    View view;
    ArrayList<String> listaProjekata;


    public ProjectInfoList(Context context, View view){
        this.context = context;
        this.view = view;
    }

    public ArrayList<String> getList(){
        readDb = new DbHandler(context);
        Cursor cursor = readDb.readAllFromTableProjekt();
        listView = (ListView) view.findViewById(R.id.projects_list);
        listaProjekata = new ArrayList<String>();

        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String sifProjekta = cursor.getString(0);
                String nazProjekta = cursor.getString(1);
                String opisProjekta = cursor.getString(2);
                String datPocetka = cursor.getString(3);
                String datZavrsetka = cursor.getString(4);
                listaProjekata.add(sifProjekta + ";"  + nazProjekta + ";" + opisProjekta + ";" + datPocetka);
                // map.put(id, new HashMap(){{put("prezime", prezime);}});
                // map.get(id).put("ime", ime);
                // map.get(id).put("OIB", OIB);
            }
        } finally {
            cursor.close();
        }

        return listaProjekata;
    }
}
