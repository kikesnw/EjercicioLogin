package com.example.ejerciciologin.Menu;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.ejerciciologin.ConexionSQLiteHelper;
import com.example.ejerciciologin.R;
import com.example.ejerciciologin.Utilidades.Utilidades;
import com.example.ejerciciologin.entidades.Usuario;

import java.util.ArrayList;

public class ListadoPersonas extends AppCompatActivity {
    ConexionSQLiteHelper conn;
    ListView listViewUsuarios;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listadopersonas);
        listViewUsuarios=(ListView)findViewById(R.id.LVUsuarios);
        ArrayList<String> ListaInformacion;
        ArrayList<Usuario> ListaUsuarios;

        conn=new ConexionSQLiteHelper(getApplicationContext(),"bd_usuarios",null,1);
        consultarListaPersonas();

    }

    private void consultarListaPersonas() {
        SQLiteDatabase db=conn.getReadableDatabase();
        Usuario usuario=null;
        //ListaUsuarios=new ArrayList<Usuario>();
        Cursor cursor = db.rawQuery("Select * from "+ Utilidades.nombre_tabla,null);
    }

}
