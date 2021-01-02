package com.dsi32g1.coshield19;

import androidx.appcompat.app.AppCompatActivity;



import android.view.View;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import static com.dsi32g1.coshield19.R.string.UserInfectedWarning;


public class taketest extends AppCompatActivity {

    CheckBox fever,smell_taste,sore_throat,tiredness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_taketest2);
        Spinner dropdown = findViewById(R.id.age_spinner);
        String[] items = new String[]{"0-10", "11-20", "21-30", "31-40", "41-50", "50+"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, items);
        dropdown.setAdapter(adapter);



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
                    Toast.makeText(getApplicationContext(), R.string.NotInfected, Toast.LENGTH_SHORT).show();
                    score = 0 ;
                }


                // will change this later to make it work with profile status in the dashboard and other layouts
                if (score >= 80) {
                    String result = getString(UserInfectedWarning);
                    Toast.makeText(getApplicationContext(),  result, Toast.LENGTH_SHORT).show();
                }



                score = 0 ;

            }

        });

    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        String str="";
        // will use these for later use  don't need them for now but no one mess with this yet

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

        /*Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();*/
    }
}

