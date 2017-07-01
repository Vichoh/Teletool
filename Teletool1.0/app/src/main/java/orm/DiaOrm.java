package orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import backend.DataBase;
import backend.Dia;

/**
 * Created by Xiterss on 06-12-2016.
 */

public class DiaOrm {

    private Context context;

    public DiaOrm(Context context) {
        this.context = context;
    }

    public void guardarDias(ArrayList<Dia> dias){

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        db.execSQL("DELETE FROM DIA");

        ContentValues datos;

        for(int i =0;i<dias.size();i++){
            datos = new ContentValues();

            datos.put("id_dia",dias.get(i).getId_dia());
            datos.put("fecha",dias.get(i).getFecha());

            db.insert("DIA",null,datos);
        }

    }
}
