package backend;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.entity.UrlEncodedFormEntity;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.message.BasicNameValuePair;
import cz.msebera.android.httpclient.util.EntityUtils;

import orm.CitaOrm;
import orm.DiaOrm;
import orm.EjercicioOrm;
import orm.MultimediaOrm;
import orm.PacienteOrm;
import orm.RutinaOrm;

/**
 * Created by Xiterss on 01-12-2016.
 */

public class Sincronizar {

    private String ip ="http://192.168.1.5/";
    private String GET_USER = "Teletool/getUser.php";
    private String DESCARGAR_EJERCICIOS = "Teletool/getEjercicios.php";
    private String DESCARGAR_MULTIMEDIA = "Teletool/getMultimedia.php";
    private String DESCARGAR_CITAS = "Teletool/getCitas.php";
    private String DESCARGAR_DIAS = "Teletool/getDia.php";
    private String DESCARGAR_RUTINA = "Teletool/getRutina.php";
    private Context context;
    private String mensaje;
    File file;
    boolean validar;

    public Sincronizar(Context context){

        this.context = context;
    }


    public void cargarInformacion(String rut){
        new consumirAsyc().execute(rut);
        //Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();
    }

    public boolean validarUsuario(String rut){

        boolean usuario = true;

        String url = ip + GET_USER;

        String json = consumo(url,rut);

        //Compruebo que el Json no este null

        if(json != null){

            //Compupruebo si el Usuario es valido

            if( ! json.equals("0")){

                try {

                    JSONArray array = new JSONArray(json);

                    Paciente paciente = new Paciente(context);

                    paciente.setRut(array.getJSONObject(0).getString("rut"));
                    paciente.setNombre(array.getJSONObject(0).getString("nombre"));
                    paciente.setEmail(array.getJSONObject(0).getString("email"));
                    paciente.setFono(array.getJSONObject(0).getString("fono"));
                    paciente.setDireccion(array.getJSONObject(0).getString("direccion"));
                    paciente.setFoto_perfil(array.getJSONObject(0).getString("foto_perfil"));

                    PacienteOrm pacienteOrm = new PacienteOrm(context);

                    pacienteOrm.guardarPaciente(paciente);
                    mensaje = "Usuario valido";

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }else {

                mensaje = "Usuario invalido";
                usuario = false;

            }

        }else {

            usuario = false;

            mensaje = "Error de conexion";

        }

        return usuario;


    }

    public void cargarEjercicios(String rut){

        String url = ip + DESCARGAR_EJERCICIOS;
        String json;
        json = consumo(url,rut);

        if(json != null){

            try {
                JSONArray array = new JSONArray(json);

                ArrayList<Ejercicio> ejercicioArrayList = new ArrayList<Ejercicio>();

                for (int i = 0; i<array.length();i++){

                    Ejercicio ejercicio = new Ejercicio();

                    ejercicio.setId_ejercicio(array.getJSONObject(i).getInt("id_ejercicio"));
                    ejercicio.setNombre(array.getJSONObject(i).getString("nombre"));
                    ejercicio.setDescripcion(array.getJSONObject(i).getString("descripcion"));
                    ejercicioArrayList.add(ejercicio);

                }

                EjercicioOrm ejercicioOrm = new EjercicioOrm(context);

                ejercicioOrm.guardarEjercicios(ejercicioArrayList);





            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    public void cargarRutinas(String rut){

        String url = ip + DESCARGAR_RUTINA;
        String json;
        json = consumo(url,rut);

        if(json != null){

            try {
                JSONArray array = new JSONArray(json);

                ArrayList<Rutina> rutinas = new ArrayList<Rutina>();
                Rutina rutina;

                for (int i=0; i<array.length();i++){

                    rutina = new Rutina();
                    rutina.setId_ejercicio(array.getJSONObject(i).getInt("id_ejercicio"));
                    rutina.setId_dia(array.getJSONObject(i).getInt("id_dia"));
                    rutina.setRut(array.getJSONObject(i).getString("rut"));

                    rutinas.add(rutina);
                }

                RutinaOrm rutinaOrm = new RutinaOrm(context);
                rutinaOrm.guardarRutina(rutinas);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }

    public void cargarDias(String rut){

        String url = ip + DESCARGAR_DIAS;
        String json;
        json = consumo(url,rut);

        if(json != null){

            try {
                JSONArray array = new JSONArray(json);

                ArrayList<Dia> dias = new ArrayList<Dia>();
                Dia dia;

                for (int i=0; i<array.length();i++){
                    dia = new Dia();
                    dia.setId_dia(array.getJSONObject(i).getInt("id_dia"));
                    dia.setFecha(array.getJSONObject(i).getString("fecha"));

                    dias.add(dia);
                }

                DiaOrm diaOrm = new DiaOrm(context);
                diaOrm.guardarDias(dias);

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

    }

    public void cargarCitas(String rut){

        String url = ip + DESCARGAR_CITAS;
        String json;
        json = consumo(url,rut);

        if(json != null){

            try {
                JSONArray array = new JSONArray(json);

                ArrayList<Cita> citas = new ArrayList<Cita>();
                Cita cita;

                for (int i=0; i<array.length();i++){
                    cita = new Cita();
                    cita.setId_cita(array.getJSONObject(i).getInt("id_cita"));
                    cita.setDescripcion(array.getJSONObject(i).getString("descripcion"));
                    cita.setFecha(array.getJSONObject(i).getString("fecha"));
                    citas.add(cita);

                }

                CitaOrm citaOrm = new CitaOrm(context);
                citaOrm.guardarCitas(citas);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void cargarMultimedia(String rut){
        String url = ip + DESCARGAR_MULTIMEDIA;
        String json;
        json = consumo(url,rut);

        String ruta = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Teletool";
        file = new File(ruta);

        if(!file.exists()){
            file.mkdirs();
        }




        if(json != null){

            try {
                JSONArray array = new JSONArray(json);

                ArrayList<Multimedia> multimediaArrayList = new ArrayList<Multimedia>();
                ArrayList<String> rutas= new ArrayList<String>();

                for (int i = 0; i<array.length();i++){

                    Multimedia multimedia = new Multimedia(context);
                    multimedia.setId_multimedia(array.getJSONObject(i).getInt("id_multimedia"));
                    multimedia.setDescripcion(array.getJSONObject(i).getString("descripcion"));
                    multimedia.setId_ejercicio(array.getJSONObject(i).getInt("id_ejercicio"));
                    multimedia.setDrawable(file.getAbsolutePath()+"/imagen"+i+".png");

                    multimediaArrayList.add(multimedia);
                    rutas.add(array.getJSONObject(i).getString("url"));

                }

                cargarImagenes(rutas);

                MultimediaOrm multimediaOrm = new MultimediaOrm(context);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }else{
            mensaje = "Error al guardar contenido multimedia";
        }

    }

    public void cargarImagenes(ArrayList<String> rutas){

        for (int i=0;i<rutas.size();i++){
            try {
                Bitmap bit = BitmapFactory.decodeStream((InputStream) new URL(ip+rutas.get(i)).getContent());
                OutputStream output;

                String ruta = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Teletool";
                file = new File(ruta);



                // Create a name for the saved image
                File archivo = new File(file,"/imagen"+i+".png");

                output = new FileOutputStream(archivo);

                // Compress into png format image from 0% - 100%
                bit.compress(Bitmap.CompressFormat.PNG, 0, output);
                output.flush();
                output.close();
                mensaje = "Exito al guardar Informacion";

            } catch (Exception e) {
                Log.e("Error","error imagen guardar "+e.getMessage());
                mensaje = "Error al guardar contenido multimedia";
                break;
            }
        }

    }








    public String consumo(String url,String rut){

        String json = null;

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);

        //post.setHeader("content-type","application/json");

        try{

            List<NameValuePair> datos = new ArrayList<>();
            datos.add(new BasicNameValuePair("rut",rut));

            post.setEntity(new UrlEncodedFormEntity(datos));

            HttpResponse respuesta = httpClient.execute(post);

            List<Ejercicio> list = new ArrayList<Ejercicio>() ;

            json = EntityUtils.toString(respuesta.getEntity());



        }catch(Exception e){
            Log.e("Error!", e.getMessage());

        }

        return json;

    }


    private class consumirAsyc extends  android.os.AsyncTask<String, Void, Void>{

        @Override
        protected Void doInBackground(String...  rut) {
            validar = validarUsuario(rut[0]);
            if(validar){
                cargarEjercicios(rut[0]);
                cargarDias(rut[0]);
                cargarCitas(rut[0]);
                cargarRutinas(rut[0]);
                cargarMultimedia(rut[0]);
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void result) {

            Toast.makeText(context,mensaje,Toast.LENGTH_SHORT).show();



        }

        @Override
        protected void onPreExecute() {

        }

    }


}
