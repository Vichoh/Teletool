package orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import backend.DataBase;
import backend.Paciente;

/**
 * Created by Xiterss on 01-12-2016.
 */

public class PacienteOrm {

    Context context;

    public PacienteOrm(Context context) {
        this.context = context;
    }

    public void guardarPaciente(Paciente paciente){

        //CREO LAS CONEXIONES CON LA BASE DE DATOS

        DataBase dataBase = new DataBase(context);
        SQLiteDatabase db = dataBase.getWritableDatabase();

        //Elimino Registros Previos de Paciente

        db.execSQL("DELETE FROM PACIENTE");

        //CREO UN CONTENEDOR DE DATOS PARA INSERTAR A LA BASE DE DATOS

        ContentValues datos = new ContentValues();

        //Agrego los datos al Contenedor

        datos.put("rut",paciente.getRut());
        datos.put("nombre",paciente.getNombre());
        datos.put("email",paciente.getEmail());
        datos.put("fono",paciente.getFono());
        datos.put("direccion",paciente.getDireccion());
        datos.put("foto_perfil",paciente.getFoto_perfil());

        //INSERTAMOS LOS DATOS A LA BASE

        db.insert("PACIENTE",null,datos);

    }
}
