package com.example.adrian.teletool10;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Estadisticas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_estadisticas, container, false);
        ((Principal) getActivity()).getSupportActionBar().setTitle("Estadisticas");


        return view;


    }
}
