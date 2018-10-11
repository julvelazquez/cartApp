package org.generation.cartapp.Network.Peticiones;

import android.os.AsyncTask;

import org.generation.cartapp.Network.General;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by RigoBono on 10/11/18.
 */

public class Registro extends AsyncTask<String, Void, String>  implements General.Network{

    public static final String METHOD="registro/nuevo.gen";
    onRegistroListener caller;

    public void setCaller(onRegistroListener caller) {
        this.caller = caller;
    }

    @Override
    protected String doInBackground(String... strings) {
        Map<String,String> params=new HashMap<String,String>();
        params.put("nombre",strings[0]);
        params.put("paterno",strings[1]);
        params.put("materno",strings[2]);
        params.put("correo",strings[3]);

        try {
            General.generalPOST(METHOD,params,this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void onResultOk(String data) {
        caller.onRegistroOk(data);
    }

    @Override
    public void onResultNotOk(String data) {
        caller.onRegistroChafa(data);
    }



    public interface onRegistroListener{
        public void onRegistroOk(String data);
        public void onRegistroChafa(String data);
    }
}
