package com.app.divinofsa;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment3 extends Fragment {

    private final String[] array = {"Oracion", "Suplica para tiempos dificiles","Un minuto con el divino niño","Oracion para verse libre de peligros","Plegaria para obtener la serenidad","Y ahora oye a tu salvador"};
    TextView aviso;
    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       if(rootView==null){
        rootView = inflater.inflate(R.layout.fragment3, container, false);
        Typeface type = Typeface.createFromAsset(getActivity().getAssets(), "fonts/GeosansLight.ttf");
        aviso = (TextView) rootView.findViewById(R.id.titulooraciones);
        aviso.setTypeface(type);
        final ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(),getResources().getStringArray(R.array.oraciones), array);
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) rootView.findViewById(R.id.listview);
        expandableLayoutListView.setAdapter(listViewAdapter);}
         return rootView;}
    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(3);
    }
}

