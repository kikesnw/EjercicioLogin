package com.example.ejerciciologin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //botones
        Button botonlistado = (Button) findViewById(R.id.bttnListado);
        Button botonBaja =(Button) findViewById(R.id.bttnBaja);
        Button botonConsulta=(Button)findViewById(R.id.bttnBuscar);
        botonlistado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                Intent intent = new Intent(v.getContext(),ListadoPersonas.class); //con el intent hacemos que con el click vaya a Registro.class
                startActivityForResult(intent,0); //lo iniciamos
            }
        });
        botonBaja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                Intent intent = new Intent(v.getContext(),BajaPersonas.class); //con el intent hacemos que con el click vaya a Registro.class
                startActivityForResult(intent,0); //lo iniciamos
            }
        });
        botonConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                Intent intent = new Intent(v.getContext(),ConsultaPersonas.class); //con el intent hacemos que con el click vaya a Registro.class
                startActivityForResult(intent,0); //lo iniciamos
            }
        });
    }

}
