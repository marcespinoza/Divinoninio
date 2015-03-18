package com.app.divinofsa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Marcelo on 14/03/2015.
 */
public class fragment1 extends Fragment {


    public static fragment1 newInstance() {
        fragment1 fragment = new fragment1();
         return fragment;
    }

    public fragment1() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment1, container, false);

        return rootView;}
}
