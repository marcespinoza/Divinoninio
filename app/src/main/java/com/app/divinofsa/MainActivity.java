package com.app.divinofsa;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         mToolbar = (Toolbar) findViewById(R.id.toolbar);

         // false for hiding the title from actoinBar
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // burger icon related

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
    }

    private void showFragment (int fragmentIndex, boolean addToBackStack){
        FragmentManager fragmentManager;
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment=null;
        if(position==0){
            fragment = fragmentManager.findFragmentByTag("fragment1");
            if(fragment==null){
                fragmentManager.beginTransaction()
                        .replace(R.id.container, new fragment1(), "fragment1")
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, "fragment1")
                        .commit();
            }
        }
        if(position==1){
            fragment = fragmentManager.findFragmentByTag("fragment2");
            Log.i("instancia","instancia "+ fragment);
            if(fragment==null){
                        fragmentManager.beginTransaction()
                        .replace(R.id.container,new fragment2(),"fragment2")
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, "fragment2")
                        .commit();
            }
        }
        if(position==2){
            fragment = fragmentManager.findFragmentByTag("fragment3");
            Log.i("instancia","instancia "+ fragment);
            if(fragment==null){
                fragmentManager.beginTransaction()
                        .replace(R.id.container,new fragment3(),"fragment3")
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, "fragment3")
                        .commit();
            }
        }
        if(position==3){
            fragment = fragmentManager.findFragmentByTag("fragment4");
            Log.i("instancia","instancia "+ fragment);
            if(fragment==null){
                fragmentManager.beginTransaction()
                        .replace(R.id.container,new fragment4(),"fragment4")
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, "fragment4")
                        .commit();
            }
        }

        if(position==4){
            fragment = fragmentManager.findFragmentByTag("fragment5");
            Log.i("instancia","instancia "+ fragment);
            if(fragment==null){
                fragmentManager.beginTransaction()
                        .replace(R.id.container,new fragment5(),"fragment5")
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, "fragment5")
                        .commit();
            }
        }



        // update the main content by replacing fragments

    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.seccion1);
                break;
            case 2:
                mTitle = getString(R.string.seccion2);
                break;
            case 3:
                mTitle = getString(R.string.seccion3);
                break;
            case 4:
                mTitle = getString(R.string.seccion4);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.galeria) {
            Intent galeria = new Intent(this, Galeria.class);
            this.startActivity(galeria);
                   }

        return super.onOptionsItemSelected(item);
    }



}
