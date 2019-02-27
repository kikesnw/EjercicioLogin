package com.example.ejerciciologin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.example.ejerciciologin.Menu.ConsultaPersonas;
import com.example.ejerciciologin.Menu.ListadoPersonas;

public class MenuUsuario extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Button botonConsulta=(Button)findViewById(R.id.bttnBuscar);
        botonConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                Intent intent = new Intent(v.getContext(), ConsultaPersonas.class); //con el intent hacemos que con el click vaya a Registro.class
                startActivityForResult(intent, 0); //lo iniciamos
            }
        });

    }
}
