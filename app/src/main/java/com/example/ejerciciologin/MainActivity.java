package com.example.ejerciciologin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity{
    EditText campoUsuario,campoContraseña;
    CheckBox privacidad;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Variables
        campoUsuario = (EditText) findViewById(R.id.etUsuario);
        campoContraseña = (EditText) findViewById(R.id.etContraseña);

        //politica de privacidad
        privacidad=findViewById(R.id.cbcondiciones);

        //botones
        Button botonregistro = (Button) findViewById(R.id.bttnRegistro);
        final Button inicioSesion = (Button) findViewById(R.id.bttnentrar);
        //Registro
        botonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                Intent intent = new Intent(v.getContext(), Registro.class); //con el intent hacemos que con el click vaya a Registro.class
                startActivityForResult(intent, 0); //lo iniciamos
            }
        });
        //Inicio de Sesion
        inicioSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                iniciarSesion();
            }
        });
    }
    private void iniciarSesion() {

        /*Para iniciar sesion lo que vamos a hacer es comprobar que existe el usuario en la base de datos
        y su contraseña correspondiente y las vamos a comparar con los datos introducidos por el usuario.
        La contraseña ya que una base de datos normal no se almacena las contraseñas en texto plano se
        se encriptan con md5 y lo que se hace es comparar las cadenas de MD5 que hay en la base de datos con
        la contraseña que mete el usuario que es convertida al instante a MD5 para su comparacion.*/

            Boolean validacionuser=false;
            Boolean validacioncontra=false;

            ConexionSQLiteHelper conectar =new ConexionSQLiteHelper(this, "DB_usuarios",null,1);
            SQLiteDatabase db=conectar.getWritableDatabase();
            ContentValues values=new ContentValues();
            Cursor fila = db.rawQuery(
                    "select usuario,contraseña from usuarios", null);
            //Validaciones en la base de datos
            if(fila.moveToFirst()) {
                do {
                    if (campoUsuario.getText().toString().equals(fila.getString(0))) {
                        validacionuser = true;

                        if (Registro.md5(campoContraseña.getText().toString()).equals(fila.getString(1))) {
                            validacioncontra = true;
                        }
                    }
                } while (fila.moveToNext());
            }
            //Si el usuario y la contraseña han sido encontrados en la base de datos se procede al login
                    if(validacionuser==true){
                        if(validacioncontra==true){
                            if(privacidad.isChecked()) {
                                Intent intent = new Intent(this, com.example.ejerciciologin.MenuUsuario.class); //con el intent hacemos que con el click vaya a Registro.class
                                startActivityForResult(intent, 0); //lo iniciamos
                            }else{
                                Toast.makeText(getApplicationContext(), "Tiene que marcar la casilla de privacidad", Toast.LENGTH_SHORT).show();

                        }
                        }else{
                            Toast.makeText(getApplicationContext(), "La contraseña no es correcta", Toast.LENGTH_SHORT).show();}


                    }else{
                        Toast.makeText(getApplicationContext(), "El usuario no es correcto", Toast.LENGTH_SHORT).show();}

    }

        }





