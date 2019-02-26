package com.example.ejerciciologin;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ejerciciologin.Utilidades.Utilidades;

public class Registro extends AppCompatActivity {

    TextView estado;
    Spinner comboDias;
    EditText campoUsuario,campoContraseña;
    RadioButton r1,r2;
    String eleccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        campoUsuario=(EditText)findViewById(R.id.et1);
        campoContraseña=(EditText)findViewById(R.id.et2);

        //base de datos
        ConexionSQLiteHelper conectar =new ConexionSQLiteHelper(this, "DB_usuarios",null,1);

        //Errores
        estado=findViewById(R.id.tv3);
        //Spinner
        comboDias=findViewById(R.id.idSpinner);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.paises,android.R.layout.simple_spinner_item);
        comboDias.setAdapter(adapter);


    }
    public void onClick(View view){
        registrarUsuarios();
    }

    private void registrarUsuarios() {
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
        values.put(Utilidades.tabla_usuario,campoUsuario.getText().toString());
        values.put(Utilidades.tabla_contraseña,campoContraseña.getText().toString());
        values.put(Utilidades.tabla_nacionalidad,comboDias.getSelectedItem().toString());
        values.put(Utilidades.tabla_sexo,eleccion);

        Long idResultante=db.insert(Utilidades.nombre_tabla,Utilidades.tabla_id,values);
        Toast.makeText(getApplicationContext(),"id : registro "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
    }
}