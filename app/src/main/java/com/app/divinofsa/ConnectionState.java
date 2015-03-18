package com.app.divinofsa;

/**
 * Created by Marcelo on 18/03/2015.
 */
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionState {

    private Context _context;

    public ConnectionState(Context context) {
        this._context = context;
    }

    public boolean checkInternetConn() {
        NetworkInfo info;
        ConnectivityManager connectivity = (ConnectivityManager) _context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if(info == null){
               info= connectivity.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            }


            if (info != null) {
                if (info.isConnected()) {
                    return true;
                }
            }
        }
        return false;
    }
}