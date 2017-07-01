package com.example.adrian.teletool10;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class Principal extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private ActionBar actionBar;
    private TextView nom_user;
    private TextView mail_user;
    private CircleImageView imagenusuario;

    private FirebaseAuth auth;
    private static final int RC_SIGN_IN = 0;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp);
        actionBar.setDisplayHomeAsUpEnabled(true);


        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);

        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
        if (navigationView != null) {
            setupNavigationDrawerContent(navigationView);
        }


        setupNavigationDrawerContent(navigationView);


        setFragment(0);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setupNavigationDrawerContent(NavigationView navigationView) {

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        switch (menuItem.getItemId()) {
                            case R.id.menu_pagina_principal:
                                menuItem.setChecked(true);
                                setFragment(0);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_perfil:
                                menuItem.setChecked(true);
                                setFragment(1);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_ejercicio:
                                menuItem.setChecked(true);
                                setFragment(2);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_calendario:
                                menuItem.setChecked(true);
                                setFragment(3);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_institucion:
                                menuItem.setChecked(true);
                                setFragment(4);
                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_informacion:
                                menuItem.setChecked(true);
                                Intent intent = new Intent(getApplicationContext(), DBTransfer.class);
                                startActivity(intent);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_enviar_estaditicas:
                                menuItem.setChecked(true);

                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                            case R.id.menu_cerrar_sesion:
                                menuItem.setChecked(true);

                                    auth = FirebaseAuth.getInstance();

                                    if (auth.getCurrentUser() != null) {
                                        // Usuario ya ha ingresado
                                        Log.d("AUTH", auth.getCurrentUser().getEmail());

                                        Toast.makeText(getApplicationContext(),"Cerrando sesión", Toast.LENGTH_SHORT).show();
                                        menuItem.setTitle("Iniciar Sesión");
                                        AuthUI.getInstance().signOut(Principal.this).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Log.d("AUTH","USUARIO CERRÓ SESIÓN");
                                                recreate();
                                            }
                                        });

                                    } else {
                                        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                                                .setProviders(AuthUI.GOOGLE_PROVIDER).setTheme(R.style.FullscreenTheme).build(), RC_SIGN_IN);
                                        menuItem.setTitle("Cerrar Sesión");


                                    }


                                drawerLayout.closeDrawer(GravityCompat.START);
                                return true;
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setFragment(0);
        drawerLayout.closeDrawer(GravityCompat.START);
    }


    @Override

    protected void onActivityResult (int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RC_SIGN_IN){
            if (resultCode == RESULT_OK){
                Log.d("AUTH", auth.getCurrentUser().getEmail());
                nom_user = (TextView) findViewById(R.id.nom_usuario);
                mail_user = (TextView) findViewById(R.id.correo_usuario);
                imagenusuario = (CircleImageView) findViewById(R.id.profile_image);


                nom_user.setText(auth.getCurrentUser().getDisplayName());
                mail_user.setText(auth.getCurrentUser().getEmail());
                Glide.with(imagenusuario.getContext()).load(auth.getCurrentUser().getPhotoUrl()).into(imagenusuario);
                Toast.makeText(getApplicationContext(), "Sesión iniciada", Toast.LENGTH_SHORT).show();
            } else {
                Log.d("AUTH", "NO AUTENTICADO");
            }
        }
    }


    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                PaginaPrincipal paginaPrincipalFragment = new PaginaPrincipal();
                fragmentTransaction.replace(R.id.fragment, paginaPrincipalFragment);
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Perfil perfilFragment = new Perfil();
                fragmentTransaction.replace(R.id.fragment, perfilFragment);
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ListaEjercicio listaEjercicioFragment = new ListaEjercicio();
                fragmentTransaction.replace(R.id.fragment, listaEjercicioFragment);
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Calendario calendarioFragment = new Calendario();
                fragmentTransaction.replace(R.id.fragment, calendarioFragment);
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                Institucion institucion = new Institucion();
                fragmentTransaction.replace(R.id.fragment, institucion);
                fragmentTransaction.commit();
                break;


        }
    }



}
