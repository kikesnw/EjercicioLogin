package com.example.ejerciciologin.Menu;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.ejerciciologin.ConexionSQLiteHelper;
import com.example.ejerciciologin.R;
import com.example.ejerciciologin.Registro;
import com.example.ejerciciologin.Utilidades.Utilidades;

public class ConsultaPersonas extends AppCompatActivity {
    EditText Usuario,modificacion;
    Button busqueda,guardar;
    RadioButton RadioUsuario,RadioContraseña;
    public Boolean eleccion=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        Usuario=(EditText)findViewById(R.id.ETUsuario);
        modificacion=(EditText)findViewById(R.id.ETNuevo);
        busqueda=(Button)findViewById(R.id.BTTNBuscar);
        guardar=(Button)findViewById(R.id.BTTNGuardar);
        RadioUsuario=(RadioButton)findViewById(R.id.RBUsuario);
        RadioContraseña=(RadioButton)findViewById(R.id.RBContraseña);

        busqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Busqueda();
            }

        });

    }
    private void Busqueda() {
        Boolean validacionuser=false;
         //Si esta en false Es usuario Si esta en true es contraseña
        final ConexionSQLiteHelper conectar =new ConexionSQLiteHelper(this, "DB_usuarios",null,1);
        SQLiteDatabase db=conectar.getWritableDatabase();
        Cursor fila = db.rawQuery(
                "select usuario from usuarios", null);
        //Validaciones en la base de datos
        if(fila.moveToFirst()) {
            do {
                if (Usuario.getText().toString().equals(fila.getString(0))) {
                    validacionuser = true;

                }
            } while (fila.moveToNext());
        }
        if(validacionuser==true){
            Toast.makeText(getApplicationContext(),"Usuario encontrado",Toast.LENGTH_SHORT).show();
            if(RadioUsuario.isChecked()){
                eleccion=false;
            }
            if(RadioContraseña.isChecked()){
                eleccion=true;
            }
            guardar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(eleccion==false){
                        SQLiteDatabase base=conectar.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        String[] parametros={(Usuario.getText().toString())};
                        values.put(Utilidades.tabla_usuario,modificacion.getText().toString() );
                        base.update(Utilidades.nombre_tabla,values,Utilidades.tabla_usuario+"=?",parametros);
                        Toast.makeText(getApplicationContext(),"Actualizado el usuario",Toast.LENGTH_SHORT).show();
                        base.close();

                    }
                    if(eleccion==true){
                        SQLiteDatabase base=conectar.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        String[] parametros={(Usuario.getText().toString())};
                        values.put(Utilidades.tabla_contraseña,Registro.md5(modificacion.getText().toString()));
                        base.update(Utilidades.nombre_tabla,values,Utilidades.tabla_contraseña+"=?",parametros);
                        Toast.makeText(getApplicationContext(),"Actualizado la contraseña",Toast.LENGTH_SHORT).show();
                        base.close();

                    }
                }

            });

        }else{
            Toast.makeText(getApplicationContext(),"Usuario no encontrado",Toast.LENGTH_SHORT).show();

        }


    }

    private void Cambio() {
    }

}
