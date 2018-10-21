package com.jude.fuad.hackharvard2018v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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
        /*
        ArrayList<DataField> printList = new ArrayList<>();
        for(int i = 0; i < dataList.size(); i++) {
            EditText e = findViewById(R.id.typeEdit);
            EditText d = findViewById(R.id.valEdit);
            printList.add(new DataField(e.getText().toString(), d.getText().toString()));
            System.out.println(new DataField(e.getText().toString(), d.getText().toString()));
        }
        OutputStreamWriter writer = null;
        try {
            writer = new OutputStreamWriter(getApplicationContext().openFileOutput(
                    "userInfo", Context.MODE_PRIVATE));
            for(int i = 0; i < printList.size(); i++) {
                if(!printList.get(i).getDataType().equals("")) {
                    writer.write( printList.get(i).toString()+ '\n');
                    System.out.println(printList.get(i).toString()+ '\n');
                }
            }
            writer.close();
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        */
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(myIntent);
    }


}
