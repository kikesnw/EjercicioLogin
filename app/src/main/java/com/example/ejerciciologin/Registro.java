package com.example.ejerciciologin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejerciciologin.Utilidades.Utilidades;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Registro extends AppCompatActivity {

    TextView estado;
    Spinner comboDias;
    EditText campoUsuario,campoContraseña;
    RadioButton r1,r2;
    CheckBox privacidad;
    String eleccion;
    String contraseña;
    Boolean validacionuser=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoUsuario=(EditText)findViewById(R.id.et1);
        campoContraseña=(EditText)findViewById(R.id.et2);

        //base de datos
        ConexionSQLiteHelper conectar =new ConexionSQLiteHelper(this, "DB_usuarios",null,1);

        //Spinner
        comboDias=findViewById(R.id.idSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.paises,android.R.layout.simple_spinner_item);
        comboDias.setAdapter(adapter);

        //politica de privacidad
        privacidad=findViewById(R.id.cbcondiciones);


    }
    public void onClick(View view){
        registrarUsuarios();
    }

    //Contraseñas MD5
    public static final String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    //Registro de usuarios
    private void registrarUsuarios() {
        Boolean validacionuser=false;
        contraseña=md5(campoContraseña.getText().toString());
        //Radio Group
        r1=(RadioButton)findViewById(R.id.r1);
        r2=(RadioButton)findViewById(R.id.r2);
        if (r1.isChecked()==true) {
            eleccion="Hombre";
        } else
        if (r2.isChecked()==true) {
            eleccion="Mujer";
        }
        ConexionSQLiteHelper conectar =new ConexionSQLiteHelper(this, "DB_usuarios",null,1);
        SQLiteDatabase db=conectar.getWritableDatabase();
        ContentValues values=new ContentValues();
        Cursor fila = db.rawQuery(
                "select usuario from usuarios", null);
        if(fila.moveToFirst()){
            do{
                if(campoUsuario.getText().toString().equals(fila.getString(0))){
               validacionuser=true;
                Toast.makeText(getApplicationContext(), "El usuario ya existe,intentelo de nuevo", Toast.LENGTH_SHORT).show();}
            }while(fila.moveToNext());
        }
        if(privacidad.isChecked()) {
            if (validacionuser == false) {
                values.put(Utilidades.tabla_usuario, campoUsuario.getText().toString());
                values.put(Utilidades.tabla_contraseña, contraseña);
                values.put(Utilidades.tabla_nacionalidad, comboDias.getSelectedItem().toString());
                values.put(Utilidades.tabla_sexo, eleccion);
                Long idResultante = db.insert(Utilidades.nombre_tabla, Utilidades.tabla_id, values);
                Toast.makeText(getApplicationContext(), "id : registro " + idResultante, Toast.LENGTH_SHORT).show();

            }
        }else{
            Toast.makeText(getApplicationContext(), "Es necesario que acepte las condiciones", Toast.LENGTH_SHORT).show();

        }
        fila.close();
        db.close();
    }
}