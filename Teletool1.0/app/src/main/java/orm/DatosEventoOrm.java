package orm;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;

import java.util.ArrayList;

import backend.DataBase;

/**
 * Created by Adrian on 29/11/2016.
 */

public class DatosEventoOrm {

    private int id_cita;
    private int color;
    private String fecha;
    private String nota;

    public DatosEventoOrm(String fecha, int color, String nota) {
        this.fecha = fecha;
        this.color = color;
        this.nota = nota;
    }

    public DatosEventoOrm() {
    }
    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public ArrayList<DatosEventoOrm> readDatosEventos(Context context){


        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        ArrayList<DatosEventoOrm> listaEventos= new ArrayList<>();


        Cursor c = db.rawQuery("Select * from CITA", null);


        if  (c.moveToFirst()){
            do{

                DatosEventoOrm datosEventoOrm = new DatosEventoOrm();


                datosEventoOrm.setId_cita(c.getInt(0));
                datosEventoOrm.setNota(c.getString(1));
                datosEventoOrm.setFecha(c.getString(2));
                datosEventoOrm.setColor(Color.GREEN);

                listaEventos.add(datosEventoOrm);


            }while(c.moveToNext());
        }

        Cursor d = db.rawQuery("Select * from NOTA", null);


        if  (d.moveToFirst()){
            do{

                DatosEventoOrm datosEventoOrm = new DatosEventoOrm();


                datosEventoOrm.setId_cita(c.getInt(0));
                datosEventoOrm.setNota(c.getString(1));
                datosEventoOrm.setFecha(c.getString(2));
                datosEventoOrm.setColor(Color.BLUE);

                listaEventos.add(datosEventoOrm);


            }while(d.moveToNext());
        }

        Cursor f = db.rawQuery("Select * from DIA", null);


        if  (f.moveToFirst()){
            do{

                DatosEventoOrm datosEventoOrm = new DatosEventoOrm();


                datosEventoOrm.setId_cita(c.getInt(0));
                datosEventoOrm.setNota("");
                datosEventoOrm.setFecha(c.getString(1));
                datosEventoOrm.setColor(Color.RED);

                listaEventos.add(datosEventoOrm);


            }while(f.moveToNext());
        }

        return  listaEventos;
    }

}
