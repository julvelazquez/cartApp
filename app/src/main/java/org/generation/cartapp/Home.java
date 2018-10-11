package org.generation.cartapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

import org.generation.cartapp.Model.Persona;
import org.json.JSONException;
import org.json.JSONObject;

public class Home extends AppCompatActivity {

    String nombreDta,paternoDta,maternoDta,correoDta,qrDta;

    TextView nombreTxt,paternoTxt,maternoTxt,correoTxt;
    ImageView qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        /*String data=getIntent().getStringExtra("Datos");
        try {
            JSONObject json=new JSONObject(data);
            nombreDta=json.optString("nombre","-");
            paternoDta=json.optString("paterno","-");
            maternoDta=json.optString("materno","-");
            correoDta=json.optString("correo","-");
            qrDta=json.optString("qr","-");

        } catch (JSONException e) {
            e.printStackTrace();
        }*/

        Persona persona=Persona.listAll(Persona.class)
                .get(Persona.listAll(Persona.class).size()-1);

        nombreTxt=findViewById(R.id.nombre);
        nombreTxt.setText(persona.getNombre());

        paternoTxt=findViewById(R.id.paterno);
        paternoTxt.setText(persona.getPaterno());

        maternoTxt=findViewById(R.id.materno);
        maternoTxt.setText(persona.getMaterno());

        correoTxt=findViewById(R.id.correo);
        correoTxt.setText(persona.getCorreo());

        byte[] decodedString = Base64.decode(persona.getQr(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        qr=findViewById(R.id.qr);
        qr.setImageBitmap(decodedByte);




    }
}
