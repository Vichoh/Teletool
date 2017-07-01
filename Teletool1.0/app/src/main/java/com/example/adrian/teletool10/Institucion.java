package com.example.adrian.teletool10;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import backend.AdaptadorInstituto;
import backend.Instituto;

public class Institucion extends Fragment implements AdapterView.OnItemClickListener, GoogleApiClient.OnConnectionFailedListener,
GoogleApiClient.ConnectionCallbacks, LocationListener {

    private GridView gridView;
    private AdaptadorInstituto adaptadorInstituto;
    private FloatingActionButton locacionCercana;

    private static final String LOGTAG = "android-localizacion";
    private LocationRequest locRequest;
    private static final int PETICION_PERMISO_LOCALIZACION = 101;
    private static final int PETICION_CONFIG_UBICACION = 201;
    private GoogleApiClient apiClient;

    private String longitud;
    private String latitud;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        final View view = inflater.inflate(R.layout.activity_institucion, container, false);
        ((Principal) getActivity()).getSupportActionBar().setTitle("Instituciones");


        gridView = (GridView) view.findViewById(R.id.grid_view);
        adaptadorInstituto = new AdaptadorInstituto(getActivity());
        gridView.setAdapter(adaptadorInstituto);
        gridView.setOnItemClickListener(this);

        apiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API)
                .build();

        locacionCercana = (FloatingActionButton) view.findViewById(R.id.fBtnLocation);
        locacionCercana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleLocationUpdates(true);
                Instituto item = Instituto.Items[calculoLocationCercanaPosicion()];
                Intent intent = new Intent(getContext(), listaInstitucion.class);
                intent.putExtra(listaInstitucion.EXTRA_PARAM_ID, item.getId());

                startActivity(intent);
        }
        });


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

        Instituto item = (Instituto) adapterView.getItemAtPosition(i);

        Intent intent = new Intent(getContext(), listaInstitucion.class);
        intent.putExtra(listaInstitucion.EXTRA_PARAM_ID, item.getId());


        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(ConnectionResult result){

        Log.e(LOGTAG, "Error grave al conectar con google play services");
    }

    @Override
    public void onConnected (@Nullable Bundle bundle){
        //Conectado correctamente

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PETICION_PERMISO_LOCALIZACION);
        }else{

            Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);

            updateUI(lastLocation);
        }
    }

    @Override
    public void onConnectionSuspended(int i){
        Log.e(LOGTAG,"Se ha interrumpido");
    }

    private void updateUI(Location loc){
        if(loc != null){
            longitud = String.valueOf(loc.getLongitude());
            latitud = String.valueOf(loc.getLatitude());
        }else{
            longitud = "-72.6166043";
            latitud = "-38.7395491";
        }
    }


    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){
        if(requestCode == PETICION_PERMISO_LOCALIZACION){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                @SuppressWarnings("MissingPermission")
                        Location lastLocation = LocationServices.FusedLocationApi.getLastLocation(apiClient);

                updateUI(lastLocation);
            }else{
                Log.e(LOGTAG, "Permiso denegado");
            }
        }
    }

    public void toggleLocationUpdates(boolean enable){
        if(enable){
            enableLocationUpdates();
        }else{
            disableLocationUpdates();
        }
    }

    private void enableLocationUpdates(){
        locRequest = new LocationRequest();
        locRequest.setInterval(2000);
        locRequest.setFastestInterval(1000);
        locRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        final LocationSettingsRequest locationSettingsRequest =
                new LocationSettingsRequest.Builder()
                .addLocationRequest(locRequest)
                .build();

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(apiClient, locationSettingsRequest);

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
                final Status status = locationSettingsResult.getStatus();
                switch(status.getStatusCode()){
                    case LocationSettingsStatusCodes.SUCCESS:

                        Log.i(LOGTAG, "Configuración correcta");
                        startLocationUpdates();
                        break;

                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        try{
                            Log.i(LOGTAG, "Se requiere accion");
                            status.startResolutionForResult(getActivity(), PETICION_CONFIG_UBICACION);
                        }catch (IntentSender.SendIntentException e){
                            toggleLocationUpdates(false);
                            Log.i(LOGTAG, "Error");
                        }
                        break;

                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        Log.i(LOGTAG, "No se puede cumplir configuracion");
                        toggleLocationUpdates(false);
                        break;
                }
            }
        });
    }

    private void startLocationUpdates(){
        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED){
            Log.i(LOGTAG, "Inicio de recepcion");
            LocationServices.FusedLocationApi.requestLocationUpdates(apiClient, locRequest, Institucion.this);
        }
    }

    private void disableLocationUpdates(){
        LocationServices.FusedLocationApi.removeLocationUpdates(apiClient, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        switch(requestCode){
            case PETICION_CONFIG_UBICACION:
                switch(resultCode){
                    case Activity.RESULT_OK:
                        startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        Log.i(LOGTAG, "El usuario no ha realizado los cambios necesarios");
                        toggleLocationUpdates(false);
                        break;
                }
                break;
        }
    }

    public int calculoLocationCercanaPosicion(){
        double xUno = Double.parseDouble(latitud);
        double yUno = Double.parseDouble(longitud);
        double aux = 0;
        int pos = 0;
        for (int i = 0; i<Instituto.Items.length ; i++){
            double xDos = Instituto.Items[i].getLatitud();
            double yDos = Instituto.Items[i].getLongitud();

            double distancia = Math.sqrt(Math.pow((xDos-xUno),2)+Math.pow((yDos-yUno),2));
            if(i==0){
                aux = distancia;
            }else{
                if(distancia < aux){
                    aux=distancia;
                    pos = i;
                }
            }
        }
        return pos;
    }

    @Override
    public void onLocationChanged(Location location){
        Log.i(LOGTAG, "Recibida nueva ubicación");
        updateUI(location);
    }

    @Override
    public void onStart(){
        super.onStart();
        if(apiClient != null){
            apiClient.connect();
        }
    }

    @Override
    public void onStop(){
        super.onStop();
        if(apiClient!= null && apiClient.isConnected()){
            apiClient.stopAutoManage(getActivity());
            apiClient.disconnect();
        }
    }



    }
