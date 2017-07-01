package orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import backend.DataBase;
import backend.Rutina;

/**
 * Created by Xiterss on 06-12-2016.
 */

public class RutinaOrm {

    Context context;

    public RutinaOrm(Context context) {
        this.context = context;
    }

    public void guardarRutina(ArrayList<Rutina> rutinas){

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        db.execSQL("DELETE FROM RUTINA");

        ContentValues datos;

        for (int i =0;i<rutinas.size();i++){

            datos = new ContentValues();

            datos.put("id_ejercicio",rutinas.get(i).getId_ejercicio());
            datos.put("id_dia",rutinas.get(i).getId_dia());
            datos.put("rut",rutinas.get(i).getRut());

            db.insert("RUTINA",null,datos);
        }
    }
}
