package com.app.divinofsa;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment4 extends Fragment {

    InputStream testimonios, isTestimonio;
    String salidas = "";
    String untestimonio="";
    ListView listaTestimonio;
    TextView textoTestimonio;
    ArrayList<String>testimonio = new ArrayList<>();
    ArrayList<String>idtestimonio = new ArrayList<>();
    TestimonioAdapter adapterTestimonio;
    ConnectionState cs;
    View rootView;
   AsyncTask asyncTask;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         if(rootView==null){
        rootView = inflater.inflate(R.layout.fragment4, container, false);
        listaTestimonio = (ListView) rootView.findViewById(R.id.listTestimonios);
        listaTestimonio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                           new Testimonio().execute(position);
            }
        });
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GeosansLight.ttf");
        textoTestimonio = (TextView) rootView.findViewById(R.id.textTestimonios);
        textoTestimonio.setTypeface(type);
        textoTestimonio.setMovementMethod(new ScrollingMovementMethod());cs = new ConnectionState(getActivity().getApplicationContext());
             Boolean flag = cs.checkInternetConn();
             if(flag){
                asyncTask = new MyAsyncTask().execute();
             }else{
                 textoTestimonio.setText("No tienes conexion a internet");
             }}
        getActionBar().setTitle(R.string.seccion4);
        return rootView;
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onCreate(savedInstance);

    }

    private class MyAsyncTask extends AsyncTask<Void, Void, Boolean> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Obteniendo lista de testimonios...");
            progressDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            Boolean result = obtenerListaTestimonios();
            return result;
        }

        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            try {
                JSONObject jsonObject, idObject;
                JSONArray jsonArray = new JSONArray(salidas);
                for(int i=0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);
                    idObject = new JSONObject(jsonObject.getString("_id"));
                    testimonio.add(jsonObject.getString("nombre"));
                    idtestimonio.add(idObject.getString("$oid"));

                } // End Loop
                this.progressDialog.dismiss();
            } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            }
            textoTestimonio.setText("Seleccione un testimonio de la lista");
            adapterTestimonio = new TestimonioAdapter(getActivity().getApplicationContext(),testimonio);
            listaTestimonio.setAdapter(adapterTestimonio);
            return;
        }
    }

    private Boolean obtenerListaTestimonios() {
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

    //Obtengo un testimonio en particular que fue seleccionado de la lista de testimonios

    private class Testimonio extends AsyncTask<Integer,Void , Void> {
        ProgressDialog progressDialog;

        @Override
        protected Void doInBackground(Integer...params) {
            HttpClient httpclient = new DefaultHttpClient();
            URI website = null;
            try {
                website = new URI("https://api.mongolab.com/api/1/databases/testimonios/collections/testimonios/"+idtestimonio.get(params[0])+"?apiKey=Oo0YSBJ9VQRHzFCYOEVmmTpUEhMd78og");
                HttpGet request = new HttpGet();
                request.setURI(website);
                HttpResponse response = httpclient.execute(request);
                HttpEntity entity = response.getEntity();
                isTestimonio = entity.getContent();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(isTestimonio, "utf-8"), 8);
                StringBuilder sBuilder = new StringBuilder();
                String line;
                while ((line = bReader.readLine()) != null) {
                    sBuilder.append(line + "\n");
                }
                isTestimonio.close();
                untestimonio = sBuilder.toString();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setMessage("Obteniendo testimonio...");
            progressDialog.show();
        }


        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            try {

             JSONObject jsonObject = new JSONObject(untestimonio);
                textoTestimonio.setText(jsonObject.getString("descripcion"));

                 } catch (JSONException e) {
                Log.e("JSONException", "Error: " + e.toString());
            }

            return;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(4);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
         //check the state of the task
        if((asyncTask != null) && (asyncTask.getStatus() == AsyncTask.Status.RUNNING)){
            asyncTask.cancel(true);
        }
    }

}