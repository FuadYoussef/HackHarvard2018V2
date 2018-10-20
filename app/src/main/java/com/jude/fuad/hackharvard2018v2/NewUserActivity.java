package com.jude.fuad.hackharvard2018v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;

public class NewUserActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newuser_activity);
    }
    public void enterData(View view){
        EditText nameEdit = findViewById(R.id.NameInput);
        EditText phoneEdit = findViewById(R.id.PhoneNumberInput);
        EditText emailEdit = findViewById(R.id.EmailInput);

        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "userInfo", Context.MODE_PRIVATE));
            writer.write( "Name~"+nameEdit.getText().toString() + '\n');
            writer.write( "Phone Number~" + phoneEdit.getText().toString() + '\n');
            writer.write( "Email Address~" + emailEdit.getText().toString() + '\n');
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(myIntent);
    }


}
