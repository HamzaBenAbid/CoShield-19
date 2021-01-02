package com.dsi32g1.coshield19;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class Login extends AppCompatActivity {
    Button callsignup, callsignin;
    TextInputLayout usernameval, passwordval;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        setContentView(R.layout.activity_login);

        passwordval = findViewById(R.id.password);
        usernameval = findViewById(R.id.login_username);
        callsignup = findViewById(R.id.signup_button);
        callsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Signup2.class);
                startActivity(intent);
            }
        });
        callsignin = findViewById(R.id.signin_btn);
        callsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String password = Objects.requireNonNull(passwordval.getEditText()).getText().toString().trim();
                final String name = Objects.requireNonNull(usernameval.getEditText()).getText().toString().trim();
                if (!validateName() | !validatepassword()) {
                    return;
                }
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
                Query checkUser = reference.orderByChild("username").equalTo(name);
                checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if (snapshot.exists()) {
                            usernameval.setError(null);
                            usernameval.setErrorEnabled(false);

                            String systemPassword = Objects.requireNonNull(snapshot.getValue()).toString();

                            String b = getSentence(systemPassword,"password");
                            assert b != null;
                            String  c = b.substring(b.indexOf("password") +9 , b.indexOf(","));

                            if (c.equals(password)) {
                                passwordval.setError(null);
                                passwordval.setErrorEnabled(false);

                                Intent intent = new Intent(Login.this, Home.class);
                                startActivity(intent);

                            } else {
                                passwordval.setError("Password does not match !");
                            }
                        } else {
                            usernameval.setError("user does not match !");

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(Login.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
    // making sure password is not null

    private Boolean validatepassword() {
        String password = Objects.requireNonNull(passwordval.getEditText()).getText().toString();

        if (password.isEmpty()) {
            passwordval.setError("Field cannot be empty");
            return false;
        } else {
            passwordval.setError(null);
            passwordval.setErrorEnabled(false);
            return true;
        }
    }
    // making sure Username is not null
    private Boolean validateName() {
        String name = Objects.requireNonNull(usernameval.getEditText()).getText().toString();
        if (name.isEmpty()) {
            usernameval.setError("Field cannot be empty");
            return false;
        } else {
            usernameval.setError(null);
            usernameval.setErrorEnabled(false);
            return true;
        }
    }
    public static String getSentence(String text, String word) {
        StringBuilder sentence = new StringBuilder();
        if (text.toLowerCase().contains(word)) {
            if (text.contains("password")) {  //Are there sentences terminating in a period?
                int loc = text.toLowerCase().indexOf(word);
                int a = loc;
                while (a >= 0) {
                    if (text.charAt(a) == ',' || a == 0) {
                        sentence = new StringBuilder(text.substring(a, loc));
                        a = 0;
                    }
                    a--;
                }
                a = loc + word.length();
                while (a <= text.length()) {
                    if (text.charAt(a) == ',') {
                        sentence.append(text.substring(loc, a + 1));
                        a = text.length()+1;
                    } else {
                        text.length();
                    }
                    a++;
                }
                return sentence.toString();
            } else {
                return text;      //If no period, return full text
            }
        } else {
            return null;
        }
    }
}
