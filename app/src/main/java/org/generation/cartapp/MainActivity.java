package org.generation.cartapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.generation.cartapp.Model.Persona;
import org.generation.cartapp.Network.Peticiones.Registro;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements Registro.onRegistroListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!Persona.listAll(Persona.class).isEmpty()){
            Intent intent=new Intent(MainActivity.this,Home.class);
            startActivity(intent);
        }

    }


    public void enviar(View v){
        EditText nombreTxt=findViewById(R.id.nombre);
        EditText maternoTxt=findViewById(R.id.materno);
        EditText paternoTxt=findViewById(R.id.paterno);
        EditText correoTxt=findViewById(R.id.correo);

        Registro r=new Registro();
        r.setCaller(this);
        r.execute(
                nombreTxt.getText().toString(),
                paternoTxt.getText().toString(),
                maternoTxt.getText().toString(),
                correoTxt.getText().toString());


    }

    @Override
    public void onRegistroOk(String data) {
        /*runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"Todo salio chidoo",Toast.LENGTH_SHORT).show();
            }
        });*/
        try {
            Persona persona=new Persona();
            JSONObject json=new JSONObject(data);
            persona.setNombre(json.optString("nombre","-"));
            persona.setPaterno(json.optString("paterno","-"));
            persona.setMaterno(json.optString("materno","-"));
            persona.setCorreo(json.optString("correo","-"));
            persona.setQr(json.optString("qr","-"));
            persona.save();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        Intent intent=new Intent(MainActivity.this,Home.class);
        startActivity(intent);
    }

    @Override
    public void onRegistroChafa(String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"Todo salio mal",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
