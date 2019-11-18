package com.example.alaincis.cisdepenses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Calendar;

/**
 * Created by AlainCis on 18/09/2019.
 */

public class BasedeDonnees extends SQLiteOpenHelper {
    public static final String bdd_name = "DepensesManager";
    public static final int bdd_version = 1;
    public static final String nom_table = "table_depense";
    public static final String id = "id";
    public static final String col_designation = "designation";
    public static final String col_montant = "montant";
    public static final String col_urgence = "urgence";
    public static final String col_delais = "delais";
    public static final String col_realise = "realise";
    public static final String critere1 = "Pas urgent";
    static Calendar calendrier = Calendar.getInstance();
    public static final int y = calendrier.get(Calendar.YEAR);
    public static final int m = calendrier.get(Calendar.MONTH);
    public static final int d = calendrier.get(Calendar.DAY_OF_MONTH);
    public static final String aujourdhui = String.valueOf((y + '/' + m + '/' + d + '/'  ));

    public BasedeDonnees(Context context) {
        super(context, bdd_name, null, bdd_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + nom_table + "(id integer primary key autoincrement," +
                "designation text, montant integer, urgence text, delais text, realise text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + nom_table);
        onCreate(db);
    }
    public Boolean insertionDesDonnees(depense d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_designation, d.getDes());
        values.put(col_montant, d.getMont());
        values.put(col_urgence, d.getUrg());
        values.put(col_delais, d.getDel());
        values.put(col_realise, d.getRel());
        long insertion = db.insert(nom_table, null, values);
        return insertion != -1;
    }
    public Cursor affichageDonnees(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id as _id, designation, montant, urgence, delais, realise FROM "+nom_table;
        Cursor curseur = db.rawQuery(query, null);
        curseur.moveToNext();
        return curseur;
    }
    public Cursor affichageDonneesToday(String critere1){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id AS _id, designation, montant, urgence, delais, realise FROM "+nom_table+" WHERE urgence="+critere1;
        Cursor curseur = db.rawQuery(query,null );
        curseur.moveToNext();
        return curseur;
    }
    public Cursor affichageEnregistrementParId(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT id as _id, designation, montant, urgence, delais, realise FROM "+nom_table+" WHERE id="+id;
        Cursor curseur = db.rawQuery(query, null);
        return curseur;
    }
    public boolean miseajourDonnees(String id, depense d){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(col_designation, d.getDes());
        values.put(col_montant, d.getMont());
        values.put(col_urgence, d.getUrg());
        values.put(col_delais, d.getDel());
        values.put(col_realise, d.getRel());
        db.update(nom_table, values, "id = ?", new String[]{id});
        return true;
    }
}

