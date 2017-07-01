package backend;

import android.content.Context;

import java.util.ArrayList;

import orm.EjercicioOrm;

/**
 * Created by Adrian on 07/11/2016.
 */

public class Ejercicio {
    private String nombre;
    private String descripcion;
    private String imagen ;
    private int id_ejercicio;

    public Ejercicio(String nombre, String descripcion, String imagen) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }


    public Ejercicio() {
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

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }


    public ArrayList<Ejercicio> buscarEjercicios(Context context){
        ArrayList<Ejercicio> ejercicios = new ArrayList<>();
        ArrayList<EjercicioOrm> ejercicioOrms;

        orm.EjercicioOrm ejercicioOrm = new EjercicioOrm();


        ejercicioOrms = ejercicioOrm.readEjercicio(context);

        for (int i = 0; i < ejercicioOrms.size(); i++){


            Ejercicio ejercicio = new Ejercicio();
            ejercicio.setId_ejercicio(ejercicioOrms.get(i).getId_ejercicio());
            ejercicio.setNombre(ejercicioOrms.get(i).getNombre());
            ejercicio.setDescripcion(ejercicioOrms.get(i).getDescripcion());
            //ejercicio.setImagen(ejercicioOrms.get(i).getImagen());
            ejercicios.add(ejercicio);



        }

        return ejercicios;
    }
}