package com.example.projectmanagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DbHandler extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Kompanija.db";
    public static final String TABLICA_OSOBA = "Osoba";
    public static final String TABLICA_PROJECT = "Project";
    public static final String TABLICA_ULOGA = "Uloga";
    public static final String TABLICA_ULOGAOSOBE = "Zaduzenje";

    public static final String Col1Osoba = "IdOsobe";
    public static final String Col2Osoba = "PrezimeOsobe";
    public static final String Col3Osoba = "ImeOsobe";
    public static final String Col4Osoba = "OIB";


    public static final String Col1Projekt = "SifProjekta";
    public static final String Col2Projekt = "NazProjekta";
    public static final String Col3Projekt = "OpisProjekta";
    public static final String Col4Projekt = "DatPocetka";
    public static final String Col5Projekt = "DatZavrsetka";

    public static final String Col1Uloga = "IdUloge";
    public static final String Col2Uloga = "NazUloge";


    public static final String Col1UlogaOsobe = "SifProjekta";
    public static final String Col2UlogaOsobe = "IdOsobe";
    public static final String Col3UlogaOsobe = "IdUloge";
    public static final String Col4UlogaOsobe = "DatDodjele";


    ArrayList<String> listItem;

    public DbHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        // SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // createTableOsoba(db);
        // createTableProjekt(db);
        // dropTableProjekt(db);
        // createTableUloga(db);

        // dropTableUlogaOsobe(db);

        // createTableUlogaOsobe(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLICA_OSOBA);
        onCreate(db);
    }


    // TRANZICIJSKE AKCIJE


    public void saveActivePerson(int personId) {
        SQLiteDatabase db=this.getWritableDatabase();
        String Create_Table = "CREATE TABLE IF NOT EXISTS " + "ActivePerson" +
                " (Id INTEGER PRIMARY KEY, PersonId INTEGER)";

        db.execSQL(Create_Table);

        ContentValues cv=new ContentValues();
        cv.put("Id", 1);
        cv.put("PersonId", personId);
        db.insert("ActivePerson", null, cv);

        // "UPDATE uloga SET NazUloge='\(naziv)' WHERE IdUloge=(?);"

        String UpdateTable = "UPDATE ActivePerson SET PersonId='" + personId + "' WHERE Id=1;";

        db.execSQL(UpdateTable);
        db.close();
    }

    public int readActivePerson() {
        String PersonIdStr = "";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT PersonId FROM ActivePerson WHERE Id=1",
                new String[]{});

        if(cursor.getCount()>0) {
            try {
                cursor.moveToFirst();
                PersonIdStr = cursor.getString(0);
            } finally {
                cursor.close();
            }

            cursor.close();
        }

        return Integer.parseInt(PersonIdStr);
    }







    // OSOBA: DbHandleri

    public void createTableOsoba(SQLiteDatabase db) {
        String Create_Table = "CREATE TABLE IF NOT EXISTS " + TABLICA_OSOBA + "(" + Col1Osoba + " INTEGER PRIMARY KEY,"
                + Col2Osoba + " TEXT,"
                + Col3Osoba + " TEXT,"
                + Col4Osoba + " TEXT " + ")";

        db.execSQL(Create_Table);
    }

    public void addOsoba(Person osoba)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Col2Osoba, osoba.getPrezimeOsobe());
        cv.put(Col3Osoba, osoba.getImeOsobe());
        cv.put(Col4Osoba, osoba.getOIB());
        db.insert(TABLICA_OSOBA, null, cv);
        db.close();
    }

    public void deleteOsoba(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String obrisiOsobu = "DELETE FROM " + TABLICA_OSOBA + " WHERE IdOsobe=" + id;
        db.execSQL(obrisiOsobu);
        db.close();
    }


    public Cursor readAllFromTableOsoba(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT IdOsobe, ImeOsobe, PrezimeOsobe, OIB FROM Osoba",
                new String[]{});
        return cursor;
    }


    public String getOsoba(Integer IdOsobe){
        String osoba_str = "";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT PrezimeOsobe, ImeOsobe, OIB FROM Osoba WHERE IdOsobe=?",
                new String[]{IdOsobe.toString()});

        if(cursor.getCount()>0) {
            // cursor.moveToFirst();
            // osoba_str = cursor.getString(0);
            try {
                cursor.moveToFirst();
                String prezime = cursor.getString(0);
                String ime = cursor.getString(1);
                String OIB = cursor.getString(2);
                osoba_str = IdOsobe.toString() + ";"  + prezime + ";" + ime + ";" + OIB;

            } finally {
                cursor.close();
            }

            cursor.close();
        }


        return osoba_str;
    }











    // PROJEKT: DbHandleri

    public void createTableProjekt(SQLiteDatabase db) {
        String Create_Table = "CREATE TABLE " + TABLICA_PROJECT + "(" + Col1Projekt + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Col2Projekt + " TEXT,"
                + Col3Projekt + " TEXT,"
                + Col4Projekt + " TEXT,"
                + Col5Projekt + " TEXT"+ ")";

        db.execSQL(Create_Table);
    }


    public void addProjekt(Project projekt) {
        Date startDate = projekt.getDatPocetka();
        Date endDate = projekt.getDatZavrsetka();

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");

        String startDateStr = simpleDate.format(startDate);
        String endDateStr = simpleDate.format(endDate);
        SQLiteDatabase db=this.getWritableDatabase();

        // DATUME JE POTRENO PRETVORITI U INTEGER
        // createTableProjekt(db);

        ContentValues cv=new ContentValues();
        cv.put(Col2Projekt, projekt.getNazProjekta());
        cv.put(Col3Projekt, projekt.getOpisProjekta());
        cv.put(Col4Projekt, startDateStr);
        cv.put(Col4Projekt, endDateStr);
        db.insert(TABLICA_PROJECT, null, cv);
        db.close();
    }

    public Cursor readAllFromTableProjekt(){
        SQLiteDatabase db=this.getReadableDatabase();
        // createTableProjekt(db);
        Cursor cursor=db.rawQuery("SELECT SifProjekta, NazProjekta, OpisProjekta, DatPocetka, " + "DatZavrsetka FROM " + TABLICA_PROJECT,
                                  new String[]{});
        return cursor;
    }


    public String getProjekt(Integer SifProjekta){
        String osoba_str = "";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT ImeOsobe FROM Osoba WHERE IdOsobe=2",
                new String[]{});

        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            osoba_str = cursor.getString(0);
            cursor.close();
        }

        return osoba_str;
    }

    public void deleteProject(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String deleteProjectQuery = "DELETE FROM " + TABLICA_PROJECT + " WHERE SifProjekta=" + id;
        db.execSQL(deleteProjectQuery);
        db.close();
    }

    public void dropTableProjekt(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLICA_PROJECT);
        onCreate(db);
    }

















    // ULOGA: DbHandleri

    public void createTableUloga(SQLiteDatabase db) {
        String Create_Table = "CREATE TABLE " + TABLICA_ULOGA + "(" + Col1Uloga + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Col2Uloga + " TEXT " + ")";

        db.execSQL(Create_Table);

        System.out.print("STVORENA JE TABLICA ULOGA");
    }

    public void addUloga(Role uloga)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        // createTableUloga(db);

        ContentValues cv=new ContentValues();
        cv.put(Col2Uloga, uloga.getNazUloge());
        db.insert(TABLICA_ULOGA, null, cv);
        db.close();
    }

    public void deleteRole(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String deletePersonQuery = "DELETE FROM " + TABLICA_ULOGA + " WHERE IdUloge=" + id;
        db.execSQL(deletePersonQuery);
        db.close();
    }

    public Cursor readAllFromTableUloga(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT IdUloge, NazUloge " + "FROM " + TABLICA_ULOGA, new String[]{});
        return cursor;
    }


    public String getUloga(Integer IdUloge){
        String uloga_str = "";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT NazUloge FROM Uloga WHERE idUloge=?", new String[]{IdUloge.toString()});

        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            uloga_str = cursor.getString(0);
            cursor.close();
        }

        return uloga_str;
    }












    // ULOGA OSOBE: DbHandleri



    /*
    public static final String DATABASE_NAME = "Kompanija.db";
    public static final String TABLICA_OSOBA = "Osoba";
    public static final String TABLICA_PROJECT = "Project";
    public static final String TABLICA_ULOGA = "Uloga";
    public static final String TABLICA_ULOGAOSOBE = "Zaduzenje";

    public static final String Col1Osoba = "IdOsobe";
    public static final String Col2Osoba = "PrezimeOsobe";
    public static final String Col3Osoba = "ImeOsobe";
    public static final String Col4Osoba = "OIB";


    public static final String Col1Projekt = "SifProjekta";
    public static final String Col2Projekt = "NazProjekta";
    public static final String Col3Projekt = "OpisProjekta";
    public static final String Col4Projekt = "DatPocetka";
    public static final String Col5Projekt = "DatZavrsetka";

    public static final String Col1Uloga = "IdUloge";
    public static final String Col2Uloga = "NazUloge";


    public static final String Col1UlogaOsobe = "SifProjekta";
    public static final String Col2UlogaOsobe = "IdOsobe";
    public static final String Col3UlogaOsobe = "IdUloge";
    public static final String Col4UlogaOsobe = "DatDodjele";

    */

    public void dropAndCreate(){
        SQLiteDatabase db=this.getWritableDatabase();

        dropTableUlogaOsobe(db);
        createTableUlogaOsobe(db);

    }

    public void dropTableUlogaOsobe(SQLiteDatabase db){
        db.execSQL("DROP TABLE IF EXISTS " + TABLICA_ULOGAOSOBE);
        //onCreate(db);
    }

    public void createTableUlogaOsobe(SQLiteDatabase db) {
        String Create_Table = "CREATE TABLE " + TABLICA_ULOGAOSOBE + " (IdZaduzenja INTEGER PRIMARY KEY AUTOINCREMENT,"
                + Col1UlogaOsobe + " INTEGER,"
                + Col2UlogaOsobe + " INTEGER,"
                + Col3UlogaOsobe + " INTEGER,"
                + Col4UlogaOsobe + " TEXT " + ")";

        String CreateTable = "CREATE TABLE IF NOT EXISTS Zaduzenje " +
                                    "(SifProjekta INTEGER,IdOsobe INTEGER,IdUloge INTEGER,DatDodjele TEXT," +
                                    "FOREIGN KEY (SifProjekta) REFERENCES Project(SifProjekta) ON DELETE CASCADE," +
                                    "FOREIGN KEY (IdOsobe) REFERENCES Osoba(IdOsobe) ON DELETE CASCADE);";

        db.execSQL(CreateTable);
    }

    /*
    *
    public static final String Col1UlogaOsobe = "SifProjekta";
    public static final String Col2UlogaOsobe = "IdOsobe";
    public static final String Col3UlogaOsobe = "IdUloge";
    public static final String Col4UlogaOsobe = "DatDodjele";
    *
    *
    * */

    public void addUlogaOsobe(PersonRole ulogaOsobe)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        // createTableUlogaOsobe(db);
        ContentValues cv=new ContentValues();
        cv.put(Col1UlogaOsobe, ulogaOsobe.getSifProjekta());
        cv.put(Col2UlogaOsobe, ulogaOsobe.getIdOsobe());
        cv.put(Col3UlogaOsobe, ulogaOsobe.getIdUloge());
        cv.put(Col4UlogaOsobe, ulogaOsobe.getDatDodjele());
        db.insert(TABLICA_ULOGAOSOBE, null, cv);
        db.close();
    }

    public Cursor readAllFromTableUlogaOsobe(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT SifProjekta, IdOsobe, IdUloge, DatDodjele FROM " + TABLICA_ULOGAOSOBE,
                new String[]{});
        return cursor;
    }

    public Cursor readAllZaduzenja(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM Zaduzenje",
                new String[]{});
        return cursor;
    }

    public Cursor readAllUlogaOsobe(){
        SQLiteDatabase db=this.getReadableDatabase();
        String readPersonRolesQuery = "SELECT Zaduzenje.SifProjekta, Zaduzenje.IdOsobe, Zaduzenje.IdUloge, Zaduzenje.DatDodjele, Project.NazProjekta, Osoba.ImeOsobe, Osoba.PrezimeOsobe, Uloga.NazUloge " +
                              "FROM (((Zaduzenje " +
                              "INNER JOIN Project ON Zaduzenje.SifProjekta = Project.SifProjekta) " +
                              "INNER JOIN Osoba ON Zaduzenje.IdOsobe = Osoba.IdOsobe) " +
                              "INNER JOIN Uloga ON Zaduzenje.IdUloge = Uloga.IdUloge)";



        Cursor cursor=db.rawQuery(readPersonRolesQuery, new String[]{});
        return cursor;
    }


    public String getUlogaOsobe(Integer SifProjekta, Integer IdOsobe){
        String uloga_osobe = "";
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT IdUloge FROM UlogaOsobe WHERE SifProjekta=? AND IdOsobe=?",
                new String[]{SifProjekta.toString(), IdOsobe.toString()});

        if(cursor.getCount()>0) {
            cursor.moveToFirst();
            uloga_osobe = cursor.getString(0);
            cursor.close();
        }

        return uloga_osobe;
    }

}
