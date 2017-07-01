package orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import backend.DataBase;
import backend.Multimedia;

/**
 * Created by Xiterss on 30-11-2016.
 */

public class MultimediaOrm {

    private int id_multimedia;
    private int id_ejercicio;
    private String descripcion;
    private String url;
    private Context context;

    public MultimediaOrm(Context context) {
        this.context=context;
    }

    public int getId_multimedia() {
        return id_multimedia;
    }

    public void setId_multimedia(int id_multimedia) {
        this.id_multimedia = id_multimedia;
    }

    public int getId_ejercicio() {
        return id_ejercicio;
    }

    public void setId_ejercicio(int id_ejercicio) {
        this.id_ejercicio = id_ejercicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }




    public ArrayList<MultimediaOrm> getMultimediaOrm(int id_ejercicio){

        ArrayList<MultimediaOrm> multimediaOrms = new ArrayList<MultimediaOrm>();
        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM MULTIMEDIA WHERE id_ejercicio = "+ id_ejercicio,null);

        if (c.moveToFirst()){
            do{
                MultimediaOrm multimedia = new MultimediaOrm(context);
                multimedia.setId_multimedia(c.getInt(0));
                multimedia.setId_ejercicio(c.getInt(1));
                multimedia.setUrl(c.getString(2));
                multimedia.setDescripcion(c.getString(3));
                multimediaOrms.add(multimedia);

            }while (c.moveToNext());
        }

        return multimediaOrms;

    }

    public void guardarMultimedia(ArrayList<Multimedia> multimedias){

        //Creo la conexion con la base de datos

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        //Elimino Registros Previos de Paciente

        db.execSQL("DELETE FROM EJERCICIO");

        //CREO UN CONTENEDOR DE DATOS PARA INSERTAR A LA BASE DE DATOS

        ContentValues datos;



        //Agrego los datos al Contenedor

        for(int i=0;i<multimedias.size();i++){

            datos = new ContentValues();

            datos.put("id_multimedia",multimedias.get(i).getId_multimedia());
            datos.put("id_ejercicio",multimedias.get(i).getId_ejercicio());
            datos.put("url",multimedias.get(i).getDrawable());
            datos.put("descripcion",multimedias.get(i).getDescripcion());

            //INSERTAMOS LOS DATOS A LA BASE

            db.insert("MULTIMEDIA",null,datos);

        }

    }
}
