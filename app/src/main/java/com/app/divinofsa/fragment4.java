package com.app.divinofsa;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment4 extends Fragment {

    InputStream testimonios;
    String salidas = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment4, container, false);
        new MyAsyncTask().execute();
        return rootView;
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Consultando Base de Datos...");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean result = obtenerTestimonios();
            return result;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            try {
                JSONArray jArray = new JSONArray(salidas);
                for(int i=0; i < jArray.length(); i++) {

                    JSONObject jObject = jArray.getJSONObject(i);

                    String name = jObject.getString("descripcion");
                    Log.i("jeison","jeison"+name);


                } // End Loop
                this.progressDialog.dismiss();
            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            }
            return;
        }
    }

    private Boolean obtenerTestimonios() {
        try {

            HttpClient httpclient = new DefaultHttpClient();
            URI website = new URI("https://api.mongolab.com/api/1/databases/testimonios/collections/testimonios?apiKey=Oo0YSBJ9VQRHzFCYOEVmmTpUEhMd78og");
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();
            testimonios = entity.getContent();

        } catch (Exception e) {
            Log.e("log_tag", "Error in http connection " + e.toString());
        }
        try {
            BufferedReader bReader = new BufferedReader(new InputStreamReader(testimonios, "utf-8"), 8);
            StringBuilder sBuilder = new StringBuilder();

            String line = null;
            while ((line = bReader.readLine()) != null) {
                sBuilder.append(line + "\n");
            }

            testimonios.close();
            salidas = sBuilder.toString();

        } catch (Exception e) {
            Log.e("BufferedReader", "Error converting result " + e.toString());
        }
        return true;
    }
}