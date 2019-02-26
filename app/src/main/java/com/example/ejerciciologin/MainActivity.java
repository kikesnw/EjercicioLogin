package com.example.ejerciciologin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button botonregistro = (Button) findViewById(R.id.bttnRegistro);
        botonregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //listener para cuando se pulse el boton
                Intent intent = new Intent(v.getContext(), Registro.class); //con el intent hacemos que con el click vaya a Registro.class
                startActivityForResult(intent, 0); //lo iniciamos
            }
        });
    }

}
