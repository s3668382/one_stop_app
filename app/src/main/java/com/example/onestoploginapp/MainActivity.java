package com.example.onestoploginapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private TextView eSignUp;
    private CheckBox eRememberMe;

    private int counter = 3;

    String userName = "";
    String userPassword = "";

    boolean isValid = false;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Bind the XML views to Java Code Elements */
        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        eLogin = findViewById(R.id.btnLogin);
        eSignUp = findViewById(R.id.tvRegister);
        eRememberMe = findViewById(R.id.cbRemember);

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialDB", Context.MODE_PRIVATE);

        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null)
        {
            Map<String, ?> allEntries = sharedPreferences.getAll();

            for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
                RegistrationActivity.credentials.addCredentials(entry.getKey(), entry.getValue().toString());
            }

            if(sharedPreferences.getBoolean("RememberMeCheckBox", false)){

                String savedUsername = sharedPreferences.getString("Username", "");
                String savedPassword = sharedPreferences.getString("Password", "");

                eName.setText(savedUsername);
                ePassword.setText(savedPassword);
                eRememberMe.setChecked(true);
            }
        }

        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                userName = eName.getText().toString();
                userPassword = ePassword.getText().toString();

                if(userName.isEmpty() || userPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();

                }else {

                    isValid = RegistrationActivity.credentials.checkCredentials(userName, userPassword);

                    if (!isValid) {

                        counter--;

                        eAttemptsInfo.setText("Attempts Remaining: " + String.valueOf(counter));

                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(MainActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        else {
                            Toast.makeText(MainActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {

                        sharedPreferencesEditor.putBoolean("RememberMeCheckBox", eRememberMe.isChecked());

                        if(eRememberMe.isChecked()){
                            sharedPreferencesEditor.putString("Username", eName.getText().toString());
                            sharedPreferencesEditor.putString("Password", ePassword.getText().toString());
                        }

                        sharedPreferencesEditor.apply();

                        startActivity(new Intent(MainActivity.this, HomePageActivity.class));
                    }

                }
            }
        });

        eSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
}
