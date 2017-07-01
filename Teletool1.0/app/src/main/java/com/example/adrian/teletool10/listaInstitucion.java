package com.example.adrian.teletool10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import backend.Instituto;
import de.hdodenhof.circleimageview.CircleImageView;

public class listaInstitucion extends AppCompatActivity {

    public static final String EXTRA_PARAM_ID = "com.example.adrian.teletool10.ID";

    private Instituto itemDetallado;
    private ImageView imagenExtendida;
    private CircleImageView imagenDirector;
    private CollapsingToolbarLayout nombre_Intitucion_lista;
    private FloatingActionButton llamar;
    private TextView direccion;
    private TextView email;
    private TextView telefono;
    private TextView descripcion;
    private TextView nombre_director;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_institucion);

        setToolbar();




        itemDetallado = Instituto.getItem(getIntent().getIntExtra(EXTRA_PARAM_ID, 0));
        imagenExtendida = (ImageView) findViewById(R.id.imagenToolbar);

        nombre_director = (TextView) findViewById(R.id.director_name);
        imagenDirector = (CircleImageView) findViewById(R.id.director_image);
        nombre_Intitucion_lista = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        direccion = (TextView) findViewById(R.id.txt_direccion);
        email = (TextView) findViewById(R.id.email_edit);
        telefono = (TextView) findViewById(R.id.fono_edit);
        descripcion = (TextView) findViewById(R.id.txt_descripcion);


        llamar = (FloatingActionButton) findViewById(R.id.llamar);


        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+itemDetallado.getFono()));
                startActivity(intent);
            }
        });


        cargarImagenExtendida();
    }

    private void cargarImagenExtendida() {
        Glide.with(imagenExtendida.getContext())
                .load(itemDetallado.getIdDrawable())
                .into(imagenExtendida);

        Glide.with(imagenDirector.getContext()).load(itemDetallado.getIdImaDir()).into(imagenDirector);

        nombre_Intitucion_lista.setTitle("Instituto "+ itemDetallado.getNombre());
        direccion.setText(itemDetallado.getDireccion());
        email.setText(itemDetallado.getCorreoElectronico());
        telefono.setText(itemDetallado.getFono());
        descripcion.setText(itemDetallado.getDescripcion());
        nombre_director.setText(itemDetallado.getNom_dire());

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: //hago un case por si en un futuro agrego mas opciones
                Log.i("ActionBar", "Atrás!");
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
