package com.example.projectmanagement;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class PersonRolesByProjectInfoList {
    DbHandler readDb;
    private Context context;
    ListView listView;
    View view;
    ArrayList<String> listaZaduzenja;

    public PersonRolesByProjectInfoList(Context context, View view){
        this.context = context;
        this.view = view;
    }
    public ArrayList<String> getList(){
        readDb = new DbHandler(context);

        /*
        readDb.dropAndCreate();

        PersonRole novaUlogaOsobe = new PersonRole(1, 1, 1, "2020-11-11");
        readDb.addUlogaOsobe(novaUlogaOsobe);
        PersonRole novaUlogaOsobe2 = new PersonRole(1, 2, 1, "2020-11-11");
        readDb.addUlogaOsobe(novaUlogaOsobe2);
        PersonRole novaUlogaOsobe3 = new PersonRole(2, 1, 2, "2020-11-11");
        readDb.addUlogaOsobe(novaUlogaOsobe3);
        */

        Cursor cursor = readDb.readAllUlogaOsobe();

        listView = (ListView) view.findViewById(R.id.project_roles_by_person_list);
        listaZaduzenja = new ArrayList<String>();
        Log.d("myTag", "This is my message");

        try {
            cursor.moveToFirst();
            while (cursor.moveToNext()) {
                String SifProjekta = cursor.getString(0);
                String IdOsobe = cursor.getString(1);
                String IdUloge = cursor.getString(2);
                String DatDodjele = cursor.getString(3);
                String NazProjekta = cursor.getString(4);
                String ImeOsobe = cursor.getString(5);
                String PrezimeOsobe = cursor.getString(6);
                String NazivUloge = cursor.getString(7);

                // UVJET NA AKTIVNI PROJEKT
                listaZaduzenja.add(SifProjekta + ";"  + IdOsobe + ";" + IdUloge + ";" + DatDodjele + ";" +
                        NazProjekta + ";" + ImeOsobe + ";" + PrezimeOsobe + ";" + NazivUloge);
            }
        } finally {
            cursor.close();
        }

        return listaZaduzenja;
    }
}