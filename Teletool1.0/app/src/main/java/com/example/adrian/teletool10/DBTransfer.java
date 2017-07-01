package com.example.adrian.teletool10;

import android.Manifest;
import android.content.pm.PackageManager;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import backend.Sincronizar;




public class DBTransfer extends AppCompatActivity {

    Button agregar;
    EditText txtRut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtransfer);

        setToolbar();


        agregar = (Button) findViewById(R.id.btnAgregar);
        txtRut = (EditText) findViewById(R.id.rut_Sincronizar);

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String rut = txtRut.getText().toString();

                //Compruebo que no ingrese un campo vacio

                if(!(rut.equals("") || rut == null)){

                    Sincronizar sincronizar = new Sincronizar(getApplicationContext());

                    //Pido los permios de escritura

                    ActivityCompat.requestPermissions(DBTransfer.this,
                            new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            0);

                    if (PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(DBTransfer.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                        sincronizar.cargarInformacion(rut);

                    } else {
                        Toast.makeText(getApplicationContext(), "Sin permisos", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(),"Ingrese el rut",Toast.LENGTH_SHORT).show();
                }


            }
        });


    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGaleria);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
