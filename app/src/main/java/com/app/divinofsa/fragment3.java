package com.app.divinofsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.andexert.expandablelayout.library.ExpandableLayoutListView;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment3 extends Fragment {

    private final String[] array = {"Un minuto con el divino niño", "Y ahora oye a tu salvador"};



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment3, container, false);
        final ListViewAdapter listViewAdapter = new ListViewAdapter(getActivity(),getResources().getStringArray(R.array.oraciones), array);
        final ExpandableLayoutListView expandableLayoutListView = (ExpandableLayoutListView) rootView.findViewById(R.id.listview);
        expandableLayoutListView.setAdapter(listViewAdapter);
        return rootView;}
}

