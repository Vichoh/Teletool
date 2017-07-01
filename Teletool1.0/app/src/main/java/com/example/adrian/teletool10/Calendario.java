package com.example.adrian.teletool10;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import backend.DatosEvento;

public class Calendario extends Fragment {


    private CompactCalendarView compactCalendar;
    private ImageButton calEjercicio;
    private TextView calNota;
    private TextView calCita;
    private TextView sinEventos;
    private TextView textoFechaSuperior;
    private TextView fechaInfeior;
    private Toolbar tolbarCalendario;
    private FloatingActionButton btnAddNotas;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_calendario, container, false);
        ((Principal) getActivity()).getSupportActionBar().setTitle("Calendario");


        tolbarCalendario = (Toolbar) view.findViewById(R.id.toolbarCalendario);


        compactCalendar = (CompactCalendarView) view.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);

        calEjercicio = (ImageButton) view.findViewById(R.id.btnEjercicio);
        calNota = (TextView) view.findViewById(R.id.calNota);
        calCita = (TextView) view.findViewById(R.id.calCita);
        sinEventos = (TextView) view.findViewById(R.id.sinEventos);
        textoFechaSuperior = (TextView)view.findViewById(R.id.textoFechaSuperior);
        fechaInfeior = (TextView)view.findViewById(R.id.fechaInferior);

        calEjercicio.setVisibility(View.INVISIBLE);
        sinEventos.setText("Sin eventos ni notas");

        btnAddNotas = (FloatingActionButton) view.findViewById(R.id.BtnLocation);



        DatosEvento datosEvento = new DatosEvento();
        crearEvento(adjuntarEventos(datosEvento.buscarDatosEvento(getContext())));

        compactCalendar.getFirstDayOfCurrentMonth().getMonth();

        textoFechaSuperior.setText(""+meses()[compactCalendar.getFirstDayOfCurrentMonth().getMonth()]+ "  "+ calculoAño(compactCalendar.getFirstDayOfCurrentMonth().getYear()));

        fechaInfeior.setText("   "+dias()[compactCalendar.getFirstDayOfCurrentMonth().getDay()] + "  " + meses()[compactCalendar.getFirstDayOfCurrentMonth().getMonth()] );

        btnAddNotas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ManejoNotas.class);
                startActivity(intent);
            }
        });




        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {



                manejoEventos(dateClicked);


            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {

               manejoEventos(firstDayOfNewMonth);

                btnAddNotas.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getContext(), ManejoNotas.class);
                        startActivity(intent);
                    }
                });

            }
        });

        return view;


    }



    private void crearEvento(List<DatosEvento> datosEvento){
        for (int i = 0; i < datosEvento.size() ; i++){
            Event event = new Event(datosEvento.get(i).getColor(), datosEvento.get(i).getFecha(), datosEvento.get(i).getNota());
            compactCalendar.addEvent(event);
        }
    }

    private List<DatosEvento> adjuntarEventos(ArrayList<DatosEvento> dEventos){
        List<DatosEvento> datos = new ArrayList<>();

        for (int i = 0 ; i <dEventos.size() ;i++ ){
            datos.add(dEventos.get(i));
        }
        return datos;
    }

    private String [] meses(){
        String [] meses = new String[12];
        meses[0] = "Enero";
        meses[1] = "Febrero";
        meses[2] = "Marzo";
        meses[3] = "Abril";
        meses[4] = "Mayo";
        meses[5] = "Junio";
        meses[6] = "Julio";
        meses[7] = "Agosto";
        meses[8] = "Septiembre";
        meses[9] = "Octubre";
        meses[10] = "Noviembre";
        meses[11] = "Diciembre";

        return meses;

    }

    private String [] dias(){
        String [] dias = new String[12];
        dias[0] = "Domingo";
        dias[1] = "Lunes";
        dias[2] = "Martes";
        dias[3] = "Miercoles";
        dias[4] = "Jueves";
        dias[5] = "Viernes";
        dias[6] = "Sabado";


        return dias;

    }

    private int calculoAño(int n){
        return 1900+n;
    }

    private void manejoEventos(Date date){

        textoFechaSuperior.setText(""+meses()[date.getMonth()]+ "  "+ calculoAño(date.getYear()));

        fechaInfeior.setText("   "+dias()[date.getDay()] + "  " + meses()[date.getMonth()] );

        List<Event> eventoDia = compactCalendar.getEvents(date);

        if (eventoDia.size() == 0) {

            calEjercicio.setVisibility(View.INVISIBLE);
            sinEventos.setText("Sin eventos ni notas");
            calNota.setText("");
            calCita.setText("");


        }else {


            sinEventos.setText("");
            for (int i = 0; i < eventoDia.size(); i++) {

                if (eventoDia.get(i).getColor() == Color.RED) {
                    calEjercicio.setVisibility(View.VISIBLE);

                }

                if (eventoDia.get(i).getColor() == Color.BLUE) {
                    calNota.setText("" + eventoDia.get(i).getData());
                }

                if (eventoDia.get(i).getColor() == Color.GREEN) {
                    calCita.setText("Visitar Centro Teleton");
                }


            }
        }

    }


}
