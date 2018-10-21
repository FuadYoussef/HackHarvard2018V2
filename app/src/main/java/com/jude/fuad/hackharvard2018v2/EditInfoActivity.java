package com.jude.fuad.hackharvard2018v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class EditInfoActivity extends AppCompatActivity {
    ArrayList<String> myInfo = new ArrayList<>();
    ArrayList<String> myInfoTypes = new ArrayList<>();

    private EditText nameET;
    private EditText phoneET;
    private EditText emailET;
    private EditText newET1;
    private EditText newTT1;
    private EditText newET2;
    private EditText newTT2;
    private EditText newET3;
    private EditText newTT3;
    private EditText newET4;
    private EditText newTT4;
    private EditText newET5;
    private EditText newTT5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editinfo_activity);
        nameET = findViewById(R.id.NameInput);
        phoneET = findViewById(R.id.PhoneNumberInput);
        emailET = findViewById(R.id.EmailInput);
        newET1 = findViewById(R.id.newInput1);
        newTT1 = findViewById(R.id.newType1);
        newET2 = findViewById(R.id.newInput2);
        newTT2 = findViewById(R.id.newType2);
        newET3 = findViewById(R.id.newInput3);
        newTT3 = findViewById(R.id.newType3);
        newET4 = findViewById(R.id.newInput4);
        newTT4 = findViewById(R.id.newType4);
        newET5 = findViewById(R.id.newInput5);
        newTT5 = findViewById(R.id.newType5);
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("userInfo")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                myInfo.add(line.split("~")[1]);
                myInfoTypes.add(line.split("~")[0]);
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }

        nameET.setText(myInfo.get(0));
        phoneET.setText(myInfo.get(1));
        emailET.setText(myInfo.get(2));
        if(myInfo.size() > 3) {
            newET1.setText(myInfo.get(3));
            newTT1.setText(myInfoTypes.get(3));
            if(myInfo.size() > 4) {
                newET2.setText(myInfo.get(4));
                newTT2.setText(myInfoTypes.get(4));
                if(myInfo.size() > 5) {
                    newET3.setText(myInfo.get(5));
                    newTT3.setText(myInfoTypes.get(5));
                    if(myInfo.size() > 6) {
                        newET4.setText(myInfo.get(6));
                        newTT4.setText(myInfoTypes.get(6));
                        if(myInfo.size() > 7) {
                            newTT5.setText(myInfoTypes.get(7));
                            newET5.setText(myInfo.get(7));
                        }
                    }
                }
            }
        }
    }
    public void enterData(View view) {
        ArrayList<String> toPrint = new ArrayList<>();
        if(!(nameET.getText().toString() == null) && !(nameET.getText().toString().equals(""))) {
            if(!(phoneET.getText().toString() == null) && !(phoneET.getText().toString().equals(""))) {
                if(!(emailET.getText().toString() == null) && !(emailET.getText().toString().equals(""))) {
                    toPrint.add("Name~" + nameET.getText().toString());
                    toPrint.add("Phone Number~" + phoneET.getText().toString());
                    toPrint.add("Email Address~" + emailET.getText().toString());
                    if(!(newET1.getText().toString() == null) && !(newET1.getText().toString().equals(""))) {
                        if(!(newTT1.getText().toString() == null) && !(newTT1.getText().toString().equals(""))) {
                            toPrint.add(newTT1.getText().toString() + "~" + newET1.getText().toString());
                        }
                    }
                    if(!(newET2.getText().toString() == null) && !(newET2.getText().toString().equals(""))) {
                        if(!(newTT2.getText().toString() == null) && !(newTT2.getText().toString().equals(""))) {
                            toPrint.add(newTT2.getText().toString() + "~" + newET2.getText().toString());
                        }
                    }
                    if(!(newET3.getText().toString() == null) && !(newET3.getText().toString().equals(""))) {
                        if(!(newTT3.getText().toString() == null) && !(newTT3.getText().toString().equals(""))) {
                            toPrint.add(newTT3.getText().toString() + "~" + newET3.getText().toString());
                        }
                    }
                    if(!(newET4.getText().toString() == null) && !(newET4.getText().toString().equals(""))) {
                        if(!(newTT4.getText().toString() == null) && !(newTT4.getText().toString().equals(""))) {
                            toPrint.add(newTT4.getText().toString() + "~" + newET4.getText().toString());
                        }
                    }
                    if(!(newET5.getText().toString() == null) && !(newET5.getText().toString().equals(""))) {
                        if(!(newTT5.getText().toString() == null) && !(newTT5.getText().toString().equals(""))) {
                            toPrint.add(newTT5.getText().toString() + "~" + newET5.getText().toString());
                        }
                    }
                    OutputStreamWriter writer = null;
                    try {
                        writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                                "userInfo", Context.MODE_PRIVATE));
                        for(int i = 0; i < toPrint.size(); i++) {
                            writer.write(toPrint.get(i) + '\n');
                        }
                        writer.close();
                    } catch (java.io.IOException e) {
                        e.printStackTrace();
                    }
                    Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
                    finish();
                    startActivity(myIntent);
                }
            }
        } else {
            Toast.makeText(this, "Please ensure you have entered a Name, Phone Number, and Email Address",
                    Toast.LENGTH_SHORT).show();
        }
    }
}