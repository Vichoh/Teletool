package com.example.adrian.teletool10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import backend.AdaptadorEjercicio;
import backend.Ejercicio;
import backend.Multimedia;

public class ListaEjercicio extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Ejercicio> datos;
    private ArrayList<Multimedia> multimedias;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_lista_ejercicio, container, false);

        ((Principal) getActivity()).getSupportActionBar().setTitle("Ejercicios");

        Ejercicio ejercicio = new Ejercicio();
        // adjuntar datos
        datos = ejercicio.buscarEjercicios(getContext());



        //Inicialización RecyclerView

        recyclerView = (RecyclerView) view.findViewById(R.id.rec_view);
        recyclerView.setHasFixedSize(true);

        final AdaptadorEjercicio adaptador = new AdaptadorEjercicio(datos);


        final Multimedia multimedia = new Multimedia(getContext());

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = recyclerView.getChildPosition(v);
                int id_ejercicio = datos.get(position).getId_ejercicio();
                multimedias = multimedia.getMultimedia(id_ejercicio);

                Intent intent = new Intent(getContext(),Galeria.class);
                intent.putExtra("multimedia",id_ejercicio);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adaptador);

        //----  forma a mostrar los card o datos

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;

    }



}

