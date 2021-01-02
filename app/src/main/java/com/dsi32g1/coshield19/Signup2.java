package com.dsi32g1.coshield19;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;


public class Signup2 extends AppCompatActivity {
    TextInputLayout usernameID , emailID , passwordID , PhonenumberID;

    Button SignedUpBtn, Signup;
    FirebaseDatabase root;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);

        SignedUpBtn = findViewById(R.id.alredy_have_btn);
        SignedUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Signup2.this,  Login.class);
                startActivity(i);
            }
        });

            usernameID = findViewById(R.id.reg_username);
            emailID = findViewById(R.id.reg_email);
            passwordID = findViewById(R.id.reg_password);
            PhonenumberID= findViewById(R.id.reg_phone_number);

             Signup = findViewById(R.id.go1_btn);
             Signup.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
             root = FirebaseDatabase.getInstance();
             reference = root.getReference("users");

            if (!validateName() | !validateEmail() | !validatepassword() | !validatephone()){
            return;
            }
                        //get all the values
                        String name = Objects.requireNonNull(usernameID.getEditText()).getText().toString();
                        String email = Objects.requireNonNull(emailID.getEditText()).getText().toString();
                        String password = Objects.requireNonNull(passwordID.getEditText()).getText().toString();
                        String phoneNo = Objects.requireNonNull(PhonenumberID.getEditText()).getText().toString();
                        UserHelperClass helper = new UserHelperClass(name,email,phoneNo,password);
                        reference.child(phoneNo).setValue(helper);

                        Toast.makeText(getApplicationContext(), "Successfully Registered , Redirecting...",
                                Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(Signup2.this, Login.class);
                        startActivity(i);
            }
        });

    }

    // username length
    private  Boolean validateName(){
        String name = Objects.requireNonNull(usernameID.getEditText()).getText().toString();
        if (name.isEmpty()){
            usernameID.setError("Field cannot be empty");
            return  false;
        }else if (name.length()>=15){
            usernameID.setError("Username too Long");
            return  false;
        }else {
            usernameID.setError(null);
            usernameID.setErrorEnabled(false);
            return true;
        }
    }
    // making sure we don't throw a random string in there
        private  Boolean validateEmail(){
            String email = Objects.requireNonNull(emailID.getEditText()).getText().toString();
            String emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            if (email.isEmpty()){
                emailID.setError("Field cannot be empty");
                return  false;
            }else if (!email.matches(emailRegex)){
                emailID.setError("Invalid email address");
                return  false;
            }else {
                emailID.setError(null);
                emailID.setErrorEnabled(false);
                return true;
            }
        }

    //validating phone so we can use an sms verification in later versions
    private  Boolean validatephone(){
        String phoneNo = Objects.requireNonNull(PhonenumberID.getEditText()).getText().toString();
        if (phoneNo.isEmpty()){
            PhonenumberID.setError("Field cannot be empty");
            return  false;
        }else if (phoneNo.length()!=8){
            PhonenumberID.setError("invalid phone number ");
            return  false;}
        else {
            PhonenumberID.setError(null);
            PhonenumberID.setErrorEnabled(false);
            return true;
        }
    }
    // forcing password strength
    private  Boolean validatepassword(){
        String password = Objects.requireNonNull(passwordID.getEditText()).getText().toString();
        String pw ="^"+
              //  "(?=.*[0-9])"+
             //   "(?=.*[a-z])"+
                "(?=.*[A-Z])"+
                "(?=.*[@#$%^&+=])"+
                "(?=\\S+$)"+
                ".{4,}"+
                "$";
        if (password.isEmpty()){
            passwordID.setError("Field cannot be empty");
            return  false;
        }else if (!password.matches(pw)){
            passwordID.setError("Choose stronger password with special and caps characters");
            return  false;
        }else {
            passwordID.setError(null);
            passwordID.setErrorEnabled(false);
            return true;
        }
    }

}