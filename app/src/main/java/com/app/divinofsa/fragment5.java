package com.app.divinofsa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment5 extends Fragment {

    static final LatLng DivinoNinio = new LatLng(-26.201196, -58.194780);
    private GoogleMap map;
    SupportMapFragment mapFragment;

   @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.mapa, container, false);
        mapFragment = ((SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.map));
       new Handler().postDelayed(new Runnable() {
           @Override
           public void run() {
               map = mapFragment.getMap();
               map.addMarker(new MarkerOptions().position(DivinoNinio)
                       .title("Divino Niño").icon(BitmapDescriptorFactory
                               .fromResource(R.drawable.iconomapa)));


               // Move the camera instantly to hamburg with a zoom of 15.
               map.moveCamera(CameraUpdateFactory.newLatLngZoom(DivinoNinio, 5));

               // Zoom in, animating the camera.
               map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
               if(mapFragment == null) new Handler().postDelayed(this, 500);
               else {
                   // Do setup the map
               }

           }
       }, 500);

        return rootView;
    }

}
