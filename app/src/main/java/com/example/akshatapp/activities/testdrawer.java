package com.example.akshatapp.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.akshatapp.Fragments.adduserfragment;
import com.example.akshatapp.Fragments.candidateslistfragment;
import com.example.akshatapp.Fragments.chatfragment;
import com.example.akshatapp.Fragments.detailsfragment;
import com.example.akshatapp.Fragments.loginfragment;
import com.example.akshatapp.Fragments.recyclerfragment;
import com.example.akshatapp.R;
import com.google.android.material.navigation.NavigationView;

public class testdrawer extends AppCompatActivity
        implements chatfragment.OnFragmentInteractionListener,
        adduserfragment.OnadduserFragmentInteractionListener,
        loginfragment.onLoginFragmentInteractionListener,
        NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testdrawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Digital Assignment 1");

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


        Fragment frag = new chatfragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framecontainer,frag);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.testdrawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==R.id.ques1)
        {
            Intent it = new Intent(testdrawer.this,Welcome.class);
            startActivity(it);
            finish();
        }
        else if(id==R.id.ques2)
        {
            Fragment fr = new candidateslistfragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer,fr)
                    .commit();
        }
        else if(id==R.id.ques3)
        {
            Fragment fr = new detailsfragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer,fr)
                    .commit();
        }
        else if(id==R.id.ques4)
        {
            Fragment fr = new recyclerfragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.framecontainer,fr)
                    .commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onadduserFragmentInteraction(Uri uri) {

    }

    @Override
    public void onLoginFragmentInteraction(Uri uri)
    {

    }



}
