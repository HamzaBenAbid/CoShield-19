package com.example.coshield19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


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
if (!validateName() | !validateEmail() | !validatepassword() | !validatephone()){
return;
}
             //get all the values
                String name = a.getEditText().getText().toString();
                String email = b.getEditText().getText().toString();
                String password = c.getEditText().getText().toString();
                String phoneNo = d.getEditText().getText().toString();
              UserHelperClass helper =new UserHelperClass(name,email,phoneNo,password);
             reference.child(phoneNo).setValue(helper);
            }
        });

    }
    private  Boolean validateName(){
        String name = a.getEditText().getText().toString();
        if (name.isEmpty()){
            a.setError("Field cannot be empty");
            return  false;
        }else if (name.length()>=15){
            a.setError("Username too Long");
            return  false;
        }else {
            a.setError(null);
            a.setErrorEnabled(false);
            return true;
        }
    }
    private  Boolean validateEmail(){
        String email = b.getEditText().getText().toString();
        String em ="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (email.isEmpty()){
            b.setError("Field cannot be empty");
            return  false;
        }else if (!email.matches(em)){
            b.setError("Invalid email address");
            return  false;
        }else {
           b.setError(null);
            b.setErrorEnabled(false);
            return true;
        }
    }
    private  Boolean validatephone(){
        String phoneNo = d.getEditText().getText().toString();
        if (phoneNo.isEmpty()){
            d.setError("Field cannot be empty");
            return  false;
        }else if (phoneNo.length()!=8){
            a.setError("invalid phone number ");
            return  false;}
        else {
            d.setError(null);
            d.setErrorEnabled(false);
            return true;
        }
    }
    private  Boolean validatepassword(){
        String password = c.getEditText().getText().toString();
        String pw ="^"+
              //  "(?=.*[0-9])"+
             //   "(?=.*[a-z])"+
                "(?=.*[A-Z])"+
                "(?=.*[@#$%^&+=])"+
                "(?=\\S+$)"+
                ".{4,}"+
                "$";
        if (password.isEmpty()){
            c.setError("Field cannot be empty");
            return  false;
        }else if (!password.matches(pw)){
            c.setError("Invalid password");
            return  false;
        }else {
            c.setError(null);
           c.setErrorEnabled(false);
            return true;
        }
    }

}