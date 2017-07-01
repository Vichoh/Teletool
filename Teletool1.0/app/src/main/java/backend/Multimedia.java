package backend;

import android.content.Context;
import android.os.Parcel;

import java.util.ArrayList;

import orm.MultimediaOrm;

/**
 * Created by Xiterss on 30-11-2016.
 */

public class Multimedia  {

    private int id_multimedia;
    private int id_ejercicio;
    private String descripcion;
    private String drawable;
    private Context context;

    public Multimedia(Context context) {

        this.context = context;
    }

    protected Multimedia(Parcel in) {
        id_multimedia = in.readInt();
        id_ejercicio = in.readInt();
        descripcion = in.readString();
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

    public String getDrawable() {
        return drawable;
    }

    public void setDrawable(String drawable) {
        this.drawable = drawable;
    }


    public ArrayList<Multimedia> getMultimedia(int id_ejercicio){

        ArrayList<Multimedia> multimedias = new ArrayList<Multimedia>();

        MultimediaOrm multimediaOrm = new MultimediaOrm(context);
        ArrayList<MultimediaOrm> multimediaOrms = multimediaOrm.getMultimediaOrm(id_ejercicio);

        for (int i = 0; i < multimediaOrms.size(); i++){

            Multimedia multimedia = new Multimedia(context);

            multimedia.setId_multimedia(multimediaOrms.get(i).getId_multimedia());
            multimedia.setId_ejercicio(multimediaOrms.get(i).getId_ejercicio());
            multimedia.setDrawable(multimediaOrms.get(i).getUrl());
            multimedia.setDescripcion(multimediaOrms.get(i).getDescripcion());

            multimedias.add(multimedia);

        }

        return multimedias;

    }
}
