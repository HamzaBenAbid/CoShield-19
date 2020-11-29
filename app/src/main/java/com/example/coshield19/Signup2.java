package com.example.coshield19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.coshiled19.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Signup2 extends AppCompatActivity {
    TextInputLayout a , b , c , d ;
    Button button  , button2 ;
    FirebaseDatabase root;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

       button  = findViewById(R.id.alredy_have_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Signup2.this,  Login.class);
                startActivity(i);
            }
        });


        a= findViewById(R.id.reg_username);
        b= findViewById(R.id.reg_email);
        c= findViewById(R.id.reg_password);
       d= findViewById(R.id.reg_phone_number);
        button2 =findViewById(R.id.go_btn);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             root = FirebaseDatabase.getInstance();
             reference = root.getReference("users");

             //get all the values
                String name = a.getEditText().getText().toString();
                String email = b.getEditText().getText().toString();
                String password = c.getEditText().getText().toString();
                String phoneNo = d.getEditText().getText().toString();
              UserHelperClass helper =new UserHelperClass(name,email,password,phoneNo);
             reference.child(phoneNo).setValue(helper);
            }
        });

    }




}