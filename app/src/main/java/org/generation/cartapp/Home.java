package org.generation.cartapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.widget.ImageView;
import android.widget.TextView;

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
        String data=getIntent().getStringExtra("Datos");
        try {
            JSONObject json=new JSONObject(data);
            nombreDta=json.optString("nombre","-");
            paternoDta=json.optString("paterno","-");
            maternoDta=json.optString("materno","-");
            correoDta=json.optString("correo","-");
            qrDta=json.optString("qr","-");

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

        byte[] decodedString = Base64.decode(qrDta, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        qr=findViewById(R.id.qr);
        qr.setImageBitmap(decodedByte);
    }
}
