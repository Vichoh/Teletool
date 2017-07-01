package com.example.adrian.teletool10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import backend.AdaptadorPrincipal;
import backend.PrincipalNews;

public class PaginaPrincipal extends Fragment implements AdapterView.OnItemClickListener {


    private ListView listview;
    private AdaptadorPrincipal adaptadorPrincipal;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.activity_pagina_principal, container, false);
        ((Principal) getActivity()).getSupportActionBar().setTitle("Página Principal");

        listview = (ListView) view.findViewById(R.id.listview_principal);
        adaptadorPrincipal = new AdaptadorPrincipal(getActivity());
        listview.setAdapter(adaptadorPrincipal);
        listview.setOnItemClickListener(this);

        return view;


    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        PrincipalNews item = (PrincipalNews) adapterView.getItemAtPosition(i);

        Uri uri = Uri.parse(item.getUrl());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);


    }
}
