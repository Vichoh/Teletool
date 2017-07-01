package com.example.adrian.teletool10;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.VideoView;

import java.util.ArrayList;

import backend.GaleriaAdaptador;
import backend.Multimedia;

public class Galeria extends AppCompatActivity {

    ImageView selectedImage;
    static final int REQUEST_VIDEO_CAPTURE = 1;
    FloatingActionButton btnGrabar;
    VideoView video;
    Uri videoUri;
    private Multimedia multimedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);


        setToolbar();
        multimedia = new Multimedia(getApplicationContext());
        video = (VideoView)findViewById(R.id.videoView);

        final ImageButton btnPlay = (ImageButton)findViewById(R.id.btnPlay);
        ImageButton btnRestart = (ImageButton)findViewById(R.id.btnRestart);

        final Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        selectedImage = (ImageView) findViewById(R.id.imageView);

        gallery.setSpacing(1);
        // drawables obtenidos desde intent <- listaEjercio
        final ArrayList<Multimedia> multimedias;


        multimedias = multimedia.getMultimedia((getIntent().getIntExtra("multimedia",0)));
        final ArrayList<Drawable> imagenes = new ArrayList<Drawable>();

        for (int i =0; i<multimedias.size();i++){
            Drawable drawable = Drawable.createFromPath(multimedias.get(i).getDrawable());
            imagenes.add(drawable);
        }


        final GaleriaAdaptador adaptador = new GaleriaAdaptador(this,imagenes);
        gallery.setAdapter(adaptador);

        selectedImage.setImageDrawable(imagenes.get(0));

        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
                //muestra la imagen selecionada
                selectedImage.setImageDrawable(imagenes.get(posicion));
                //Cambiamos la descripcion !!

            }
        });

        btnGrabar =(FloatingActionButton)findViewById(R.id.fBtnGrabar);
        btnGrabar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dispatchTakeVideoIntent();
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (video.isPlaying()){
                    video.pause();
                    btnPlay.setBackgroundResource(R.drawable.ic_pause_circle_filled_white_24dp);
                }else if(!video.isPlaying()){
                    video.resume();
                    btnPlay.setBackgroundResource(R.drawable.ic_play_circle_filled_white_24dp);
                }else{
                    video.start();
                }
            }
        });
        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.start();
            }
        });

    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGaleria);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();
            reproducirVideo(video, videoUri);
        }
    }

    private void dispatchTakeVideoIntent() {
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }
    private void reproducirVideo(VideoView video, Uri uri){
        video.setVideoURI(uri);
        video.requestFocus();
        video.start();
    }

}