package org.generation.cartapp.Network;



import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by RigoBono on 10/11/18.
 */

public class General {

    public static final String SERVER="http://192.168.66.148:8082/";
    private static final int RESULTADO_ERROR = -1;
    private static final int RESULTADO_OK = 0;

    public interface Network{
        public void onResultOk(String data);
        public void onResultNotOk(String data);
    }


    public static int generalPOST(String method, Map<String,String> params, Network caller) throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(SERVER+method);
        List<NameValuePair> param = new ArrayList<NameValuePair>(1);

        for(Map.Entry<String,String> parametro:params.entrySet()){
            System.out.println("Parametro "+parametro.getKey()+" Valor " +parametro.getValue());
            param.add(new BasicNameValuePair(parametro.getKey(), parametro.getValue()));
        }

        request.setEntity(new UrlEncodedFormEntity(param, "UTF-8"));
        HttpResponse response=null;
        try{
            response=httpClient.execute(request);
            if (response.getStatusLine().getStatusCode()!=200) {
                System.out.println("HTTP["+method+"]:"+response.getStatusLine().getStatusCode());
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    InputStream instream=entity.getContent();
                    int ch;
                    StringBuilder sb = new StringBuilder();
                    while ((ch = instream.read()) != -1) {
                        sb.append((char) ch);
                    }
                    System.out.println("HTTP["+method+"]:"+sb.toString());
                    caller.onResultNotOk("HTTP["+method+"]:"+sb.toString());
                }

                request.abort();
                return RESULTADO_ERROR;
            }else{
                HttpEntity entity = response.getEntity();
                if(entity!=null){
                    InputStream instream=entity.getContent();
                    int ch;
                    StringBuilder sb = new StringBuilder();
                    while ((ch = instream.read()) != -1) {
                        sb.append((char) ch);
                    }
                    System.out.println("HTTP["+method+"]:"+sb.toString());
                    caller.onResultOk(sb.toString());
                    instream.close();
                    return RESULTADO_OK;

                }else{
                    caller.onResultNotOk("HTTP["+method+"]:GENERAL ERROR");
                    request.abort();
                    return RESULTADO_ERROR;
                }
            }
        }catch (HttpHostConnectException httpHostConnectException){
            caller.onResultNotOk("Error de conexión, revisa tu internet");
        }catch (NullPointerException npe){
            caller.onResultNotOk("Error de conexión, revisa tu internet");
        }

        return RESULTADO_OK;

    }

}
