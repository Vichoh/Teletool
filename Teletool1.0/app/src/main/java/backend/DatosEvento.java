package backend;

import android.content.Context;

import java.text.ParseException;
import java.util.ArrayList;

import orm.DatosEventoOrm;

/**
 * Created by Adrian on 21/11/2016.
 */

public class DatosEvento  {

    private int id_datosEventos;
    private int color;
    private long fecha;
    private String nota;

    public DatosEvento(int fecha, int color, String nota) {
        this.fecha = fecha;
        this.color = color;
        this.nota = nota;
    }

    public DatosEvento() {
    }

    public int getId_datosEventos() {
        return id_datosEventos;
    }

    public void setId_datosEventos(int id_datosEventos) {
        this.id_datosEventos = id_datosEventos;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public long getFecha() {
        return fecha;
    }

    public void setFecha(long fecha) {
        this.fecha = fecha;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }



    public ArrayList<DatosEvento> buscarDatosEvento(Context context){
        ArrayList<DatosEvento> ejercicios = new ArrayList<>();
        ArrayList<DatosEventoOrm> datosEventoOrms;

        orm.DatosEventoOrm datosEventoOrm = new DatosEventoOrm();


        datosEventoOrms = datosEventoOrm.readDatosEventos(context);

        for (int i = 0; i < datosEventoOrms.size(); i++){


            DatosEvento datosEvento = new DatosEvento();
            datosEvento.setId_datosEventos(datosEventoOrms.get(i).getId_cita());
            datosEvento.setNota(datosEventoOrms.get(i).getNota());

            long epoch = 0;
            try {
                 epoch = new java.text.SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse(datosEventoOrms.get(i).getFecha()+ " "+"08:00:00").getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            datosEvento.setFecha(epoch);
            datosEvento.setColor(datosEventoOrms.get(i).getColor());

            ejercicios.add(datosEvento);



        }

        return ejercicios;
    }


}
