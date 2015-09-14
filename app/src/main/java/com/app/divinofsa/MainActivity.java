package com.app.divinofsa;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;

import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.fsa.gcm.GCMActivity;
import com.kskkbys.rate.RateThisApp;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    private NavigationDrawerFragment mNavigationDrawerFragment;
    private static final String NUMERO_DE_EJECUCIONES = "Rta numero de ejecuciones";
    private static final String NOMBRE_PREFERENCIA = "Registro";
    ShareActionProvider mShareActionProvider;
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



    @Override
    public void onNavigationDrawerItemSelected(int position) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment fragment;
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

        if(position==5){
            fragment = fragmentManager.findFragmentByTag("fragment6");
            Log.i("instancia","instancia "+ fragment);
            if(fragment==null){
                fragmentManager.beginTransaction()
                        .replace(R.id.container,new fragment6(),"fragment6")
                        .addToBackStack(null)
                        .commit();
            }else{
                fragmentManager.beginTransaction()
                        .replace(R.id.container, fragment, "fragment6")
                        .commit();
            }
        }
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
            case 5:
                mTitle = getString(R.string.seccion5);
                break;
            case 6:
                mTitle = getString(R.string.seccion6);
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
            MenuItem itemShare = menu.findItem(R.id.compartir);
            mShareActionProvider = (ShareActionProvider)  MenuItemCompat.getActionProvider(itemShare);
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT,"https://play.google.com/store/apps/details?id=com.app.divinofsa");
            mShareActionProvider.setShareIntent(shareIntent);
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

        if (id == R.id.leer) {
            new MaterialDialog.Builder(this)
                    .title("Leer")
                    .customView(R.layout.leer, true)
                    .positiveText("Entiendo")
                    .build()
                    .show();
        }
        if(id==R.id.escribime){
            Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
            emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            emailIntent.setType("plain/text");
            emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {"fgoicochea@donbosco.org.ar","Divinoniniofsa@gmail.com"});
            emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Divino Niño Aplicacion");
            startActivity(Intent.createChooser(emailIntent, "Enviar correo usando..."));
        }

        return super.onOptionsItemSelected(item);
    }
//Al presionar el boton Atras, Controlo si en el backstack no queda ningun fragmento, si es asi cierra la aplicacion


    public void onStart(){
        super.onStart();
        SharedPreferences contadorEjecuciones = getApplicationContext().getSharedPreferences(NOMBRE_PREFERENCIA, Context.MODE_PRIVATE);
        SharedPreferences idRegistro = getApplicationContext().getSharedPreferences("idRegistro", Context.MODE_PRIVATE);
        SharedPreferences versionApp = getApplicationContext().getSharedPreferences("versionApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = contadorEjecuciones.edit();
        String regId=idRegistro.getString("regId","");
        String versionapp = versionApp.getString("versionAplicacion",BuildConfig.VERSION_NAME);
        //OL es el cero del tipo LONG
        if ((regId=="") || (versionapp!=BuildConfig.VERSION_NAME)){
            SharedPreferences.Editor editorVersion = versionApp.edit();
            editorVersion.putString("regId",BuildConfig.VERSION_NAME);
            editorVersion.commit();
            GCMActivity actividadgcm = new GCMActivity(this);
            actividadgcm.RegisterUser();
            Log.i("registro","registro "+versionapp);
        }

        int numeroEjecuciones = contadorEjecuciones.getInt(NUMERO_DE_EJECUCIONES, 0);
        System.out.println(numeroEjecuciones+"ejecuciones");
        numeroEjecuciones++;
        editor.putInt(NUMERO_DE_EJECUCIONES, numeroEjecuciones);
        editor.commit();
        RateThisApp.onStart(this);
        // If the criteria is satisfied, "Rate this app" dialog will be shown
        RateThisApp.showRateDialogIfNeeded(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            this.finish();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

}
