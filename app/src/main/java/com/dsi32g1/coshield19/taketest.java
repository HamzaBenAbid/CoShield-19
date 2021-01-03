package com.dsi32g1.coshield19;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;


public class taketest extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;
    CheckBox fever,smell_taste,sore_throat,tiredness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taketest2);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        Spinner dropdown = findViewById(R.id.age_spinner);
        String[] items = new String[]{"0-10", "11-20", "21-30", "31-40", "41-50", "50+"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);

        setSupportActionBar(toolbar);
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle;
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_view);

        fever = findViewById(R.id.fever);
        smell_taste = findViewById(R.id.smell_taste);
        sore_throat = findViewById(R.id.sore_throat);
        tiredness = findViewById(R.id.tiredness);
        Button submitbtn = findViewById(R.id.go2_btn);




            // using basic toast just for testing will make it work better later
        submitbtn.setOnClickListener(new View.OnClickListener() {
            int score ;

            @Override
            public void onClick(View v) {

                if (fever.isChecked()) {

                    score += 50;

                }

                 if (smell_taste.isChecked()) {

                    score += 30;
                }



                 if (sore_throat.isChecked()) {

                    score += 10;
                }


                if (tiredness.isChecked()) {

                    score += 10;
                }


                if (!tiredness.isChecked()&&!sore_throat.isChecked()&&!fever.isChecked()&&!smell_taste.isChecked()){
                    score = 0 ;
                }


                // will change this later to make it work with profile status in the dashboard and other layouts
                    // replace toast with some thing more appealing to the user
                if (score >= 80) {

                    alertDialogInfected();
                }

                if (score <= 60  ){

                    alertDialogNotInfected();
                }



                score = 0 ;

            }

        });

    }

    // dialog method when user is infected
    private void alertDialogInfected() {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(R.string.UserInfectedWarning);
        dialog.setTitle(R.string.CheckNearbyLabs);
        dialog.setPositiveButton("Check",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        //  will redirect this to a web view to search for medical labs
                        Toast.makeText(getApplicationContext(),"Checking nearby labs ...",Toast.LENGTH_LONG).show();
                        Intent NearbyLabsIntent = new Intent(taketest.this, nearbyLabs.class);
                        startActivity(NearbyLabsIntent);
                    }
                });
                dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(),"Aborting ...",Toast.LENGTH_LONG).show();


            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }

    private void alertDialogNotInfected() {

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage(R.string.NotInfected);
        dialog.setTitle(R.string.NotInfectedTitle);
        dialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        // event upon pressing OK will find another idea behind it
                    }
                });
        dialog.setNegativeButton("cancel",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // cancel event


            }
        });
        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }



    // will use these for later use  don't need them for now but no one mess with this yet
    public void onCheckboxClicked(View view) {
      /*  boolean checked = ((CheckBox) view).isChecked();
        String str="";



        switch(view.getId()) {
            case R.id.fever:
                str += checked?"Selected " + getString(R.string.fever) : "Deselected "+ getString(R.string.fever);
                break;

            case R.id.smell_taste:
                str += checked?"Selected " + getString(R.string.smelltaste): "Deselected "+ getString(R.string.smelltaste);
                break;

            case R.id.sore_throat:
                str += checked?"Selected " + getString(R.string.sorethroat): "Deselected "+ getString(R.string.sorethroat);
                break;

            case R.id.tiredness:
                str += checked?"Selected " + getString(R.string.tireddness): "Deselected "+ getString(R.string.tireddness);
                break;
        }


        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show(); */
    }


    // intent and navigation selection
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                Intent Homeintent = new Intent(taketest.this, Home.class);
                startActivity(Homeintent);
                break;
            case R.id.nav_profile:
                Intent Profileintent = new Intent(taketest.this, Profile.class);
                startActivity(Profileintent);
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}

