package com.jude.fuad.hackharvard2018v2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> myContacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupListView();
    }

    public void launchShareQR(View view) {
        Intent myIntent = new Intent(getApplicationContext(), ShareQRActivity.class);
        startActivity(myIntent);
    }
    public void launchAddQR(View view) {
        Intent myIntent = new Intent(getApplicationContext(), AddQRActivity.class);
        startActivity(myIntent);
    }
    public void setupListView() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("contactFile")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                myContacts.add(line);
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }
        String[] myContactsNames = new String[myContacts.size()];
        for (int i = 0; i < myContacts.size(); i++) {
            String[] split =  myContacts.get(i).split("`");
            myContactsNames[i] =split[1];
        }
        ListView listDays = findViewById(R.id.contactList);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, myContactsNames);
        // Set The Adapter
        listDays.setAdapter(arrayAdapter);
        listDays.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Intent myIntent = new Intent(getApplicationContext(), chatMenuActivity.class);
                // finish();
                startActivity(myIntent);*/
            }
        });
    }
    
    public void testNFC(View view) {
        Intent myIntent = new Intent(getApplicationContext(), NFCTestActivity.class);
        startActivity(myIntent);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }
    public void editInfo(View view) {

    }
}