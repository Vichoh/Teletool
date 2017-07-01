package orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import backend.DataBase;
import backend.Ejercicio;

/**
 * Created by Adrian on 23/11/2016.
 */

public class EjercicioOrm {

    private int id_ejercicio;
    private String nombre;
    private String descripcion;
    private int imagen;
    Context context;

    public EjercicioOrm(Context context){
        this.context = context;
    }

    public EjercicioOrm(String nombre, String descripcion, int imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }



    public EjercicioOrm() {
    }

    public int getId_ejercicio() {
        return id_ejercicio;
    }

    public void setId_ejercicio(int id_ejercicio) {
        this.id_ejercicio = id_ejercicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }



    public ArrayList<EjercicioOrm> readEjercicio(Context context){


        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getReadableDatabase();

        ArrayList<EjercicioOrm> listaEjercicios = new ArrayList<>();


        Cursor c = db.rawQuery("Select * from Ejercicio", null);


        if  (c.moveToFirst()){
            do{

                EjercicioOrm ejercicioOrm = new EjercicioOrm();


                ejercicioOrm.setId_ejercicio(c.getInt(0));
                ejercicioOrm.setNombre(c.getString(1));
                ejercicioOrm.setDescripcion(c.getString(2));

                //Toast.makeText(context,ejercicioOrm.getNombre()+" "+ejercicioOrm.getDescripcion(),Toast.LENGTH_SHORT).show();
                listaEjercicios.add(ejercicioOrm);


            }while(c.moveToNext());
        }

        return  listaEjercicios;
    }

    public void guardarEjercicios(ArrayList<Ejercicio> ejercicios){

        //Creo la conexion con la base de datos

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        //Elimino Registros Previos de Paciente

        db.execSQL("DELETE FROM EJERCICIO");

        //CREO UN CONTENEDOR DE DATOS PARA INSERTAR A LA BASE DE DATOS

        ContentValues datos;



        //Agrego los datos al Contenedor

        for(int i=0;i<ejercicios.size();i++){

            datos = new ContentValues();

            datos.put("id_ejercicio",ejercicios.get(i).getId_ejercicio());
            datos.put("nombre",ejercicios.get(i).getNombre());
            datos.put("descripcion",ejercicios.get(i).getDescripcion());

            //INSERTAMOS LOS DATOS A LA BASE

            db.insert("EJERCICIO",null,datos);

        }

    }




}
