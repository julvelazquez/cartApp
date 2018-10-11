package org.generation.cartapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    String nombreDta,paternoDta,maternoDta,correoDta;

    TextView nombreTxt,paternoTxt,maternoTxt,correoTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        String data=getIntent().getStringExtra("Datos");
        try {
            JSONObject json=new JSONObject(data);
            nombreDta=json.optString("nombre","-");
            paternoDta=json.optString("paterno","-");
            maternoDta=json.optString("materno","-");
            correoDta=json.optString("correo","-");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        nombreTxt=findViewById(R.id.nombre);
        nombreTxt.setText(nombreDta);

        paternoTxt=findViewById(R.id.paterno);
        paternoTxt.setText(paternoDta);

        maternoTxt=findViewById(R.id.materno);
        maternoTxt.setText(maternoDta);

        correoTxt=findViewById(R.id.correo);
        correoTxt.setText(correoDta);


    }
}
