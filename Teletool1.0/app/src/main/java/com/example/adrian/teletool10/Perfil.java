package com.example.adrian.teletool10;

import android.app.Dialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import backend.DataBase;

public class Perfil extends Fragment implements Button.OnClickListener{

    ImageButton telefonoEdit;
    ImageButton emailEdit;
    ImageButton ciudadEdit;
    ImageButton direccionEdit;
    Button btnAceptar;
    Button btnCancelar;
    TextView edicion;
    Dialog dialog;
    EditText txt;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.activity_perfil, container, false);
        ((Principal) getActivity()).getSupportActionBar().setTitle("Perfil");

        DataBase database = new DataBase(getContext());
        SQLiteDatabase db = database.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT rut, nombre, email, fono, direccion FROM PACIENTE;", null);
        TextView pfNombre = (TextView) view.findViewById(R.id.nombre_perfil);
        TextView pfRut = (TextView) view.findViewById(R.id.usuario_perfil);
        TextView pfEmail = (TextView) view.findViewById(R.id.email_perfil);
        TextView pfFono = (TextView) view.findViewById(R.id.telefono_perfil);
        TextView pfDire = (TextView) view.findViewById(R.id.direccion_perfil);

        if(c.moveToFirst() && c!= null){
            pfNombre.setText(c.getString(1));
            pfRut.setText(c.getString(0));
            pfEmail.setText(c.getString(2));
            pfFono.setText(c.getString(3));
            pfDire.setText(c.getString(4));
        }

        dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogo_edit);
        telefonoEdit = (ImageButton) view.findViewById(R.id.btnTelefono_edit);
        emailEdit = (ImageButton) view.findViewById(R.id.btnEmail_edit);
        ciudadEdit = (ImageButton) view.findViewById(R.id.ciudad_edit);
        direccionEdit = (ImageButton) view.findViewById(R.id.btnDireccion_edit);
        btnAceptar = (Button) dialog.findViewById(R.id.btnAceptar);
        btnCancelar = (Button) dialog.findViewById(R.id.btnCancelar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt = (EditText) dialog.findViewById(R.id.edit);

                String valor = txt.getText().toString();


                edicion.setText(valor);
                dialog.cancel();
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        telefonoEdit.setOnClickListener(this);
        emailEdit.setOnClickListener(this);
        ciudadEdit.setOnClickListener(this);
        direccionEdit.setOnClickListener(this);


        return view;


    }

    @Override
    public void onClick(View v) {


        if(R.id.btnTelefono_edit == v.getId()){
            dialog.setTitle("Editar Numero Telefonico");
            edicion = (TextView) view.findViewById(R.id.telefono_perfil);
            dialog.show();
        }else if (R.id.btnEmail_edit == v.getId()){
            dialog.setTitle("Editar Email");
            edicion = (TextView) view.findViewById(R.id.email_perfil);
            dialog.show();
        }else if(R.id.ciudad_edit == v.getId()){
            dialog.setTitle("Editar Ciudad");
            edicion = (TextView) view.findViewById(R.id.ciudad_perfil);
            dialog.show();
        }else if (R.id.btnDireccion_edit == v.getId()){
            dialog.setTitle("Editar Direccion");
            edicion = (TextView) view.findViewById(R.id.direccion_perfil);
            dialog.show();
        }

    }

}


