package orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import backend.Cita;
import backend.DataBase;

/**
 * Created by Xiterss on 06-12-2016.
 */

public class CitaOrm {

    private Context context;

    public CitaOrm(Context context) {
        this.context = context;
    }

    public void guardarCitas(ArrayList<Cita> citas){

        //Creo la conexion con la base de datos

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        //Elimino registros previos

        db.execSQL("DELETE FROM CITA");

        //Creo un contenedor

        ContentValues datos;

        for(int i = 0; i<citas.size();i++){

            datos = new ContentValues();

            datos.put("id_cita",citas.get(i).getId_cita());
            datos.put("descripcion",citas.get(i).getDescripcion());
            datos.put("fecha",citas.get(i).getFecha());

            //Inserto los datos a la base

            db.insert("CITA",null,datos);


        }

    }
}
