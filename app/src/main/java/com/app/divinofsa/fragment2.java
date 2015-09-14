package com.app.divinofsa;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lsjwzh.widget.materialloadingprogressbar.CircleProgressBar;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment2 extends Fragment{
    public FTPClient mFTPClient = null;
    boolean status;
    static FTPFile[] ftpFiles;
    TextView aviso;
    String texto="";
   ConnectionState cs;
    SharedPreferences sp;
    CircleProgressBar circleProgressBar;
    View rootView;
    AsyncTask asyncTaskAviso;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Cuando la instancia del fragment es agregado al backstack no se destruye su vista, entonces solo la primera vez
        //inflo la vista, la segunda vez que selecciono nuevamente el fragment, si no es nula la vista
        //solo devuelvo la misma y asi conservo todos los datos de mi vista sin refrescar.
        if(rootView==null){
        rootView = inflater.inflate(R.layout.fragment2, container, false);
        aviso = (TextView) rootView.findViewById(R.id.textoAviso);
        circleProgressBar= (CircleProgressBar) rootView.findViewById(R.id.progressBar);
        cs = new ConnectionState(getActivity().getApplicationContext());
        Boolean flag = cs.checkInternetConn();
        if(flag){
             asyncTaskAviso = new obtenerAviso().execute();
         }else{
             sp = getActivity().getSharedPreferences("aviso",0);
             aviso.setText(sp.getString("aviso","Ops! no tienes conexion"));
         }}
        return rootView;}

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

    }
    @Override
    public void onActivityCreated(Bundle savedInstance){
        super.onCreate(savedInstance);

    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public void almacenarAviso(String aviso){
        SharedPreferences sp = getActivity().getSharedPreferences("aviso",0);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("aviso",aviso);
        editor.commit();
    }

    public boolean ftpConnect(String host, String username, String password){
        try {
            mFTPClient = new FTPClient();
            mFTPClient.connect(host);
            if (FTPReply.isPositiveCompletion(mFTPClient.getReplyCode())) {
                status = mFTPClient.login(username, password);
                mFTPClient.setFileType(FTP.BINARY_FILE_TYPE);
                mFTPClient.enterLocalPassiveMode();
                return status;
            }else{
                Log.i("no se pudo conectar","error");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private class obtenerAviso extends AsyncTask<Void, Void, Void>
    {

        @Override
        protected void onPreExecute()
        {
          circleProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                ftpConnect( "divinoninio.comlu.com", "a5116625","appdivino2014");

                if(status == false )
                {

                    Toast.makeText(getActivity(), "Sin conexion con el servidor", Toast.LENGTH_LONG).show();}
                else {

                    mFTPClient.enterLocalPassiveMode();
                    InputStream inStream = mFTPClient.retrieveFileStream("public_html/aviso.txt");
                    InputStreamReader isr = new InputStreamReader(inStream, "UTF8");
                    texto = new Scanner(inStream).useDelimiter("\\A").next();
                    isr.close();
                }
            }
            catch (Exception e)
            {
                Log.e(MainActivity.class.toString(), e.getMessage());
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void unused)
        {
            circleProgressBar.setVisibility(View.GONE);
            aviso.setText(texto);
            almacenarAviso(texto);
        }}

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(2);
    }

    @Override
    public void onStop() {
        super.onStop();
        //check the state of the task
        if((asyncTaskAviso != null) && (asyncTaskAviso.getStatus() == AsyncTask.Status.RUNNING)){
            asyncTaskAviso.cancel(true);
        }
    }
}
