package com.jude.fuad.hackharvard2018v2;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NfcAdapter mNfcAdapter;
    // Flag to indicate that Android Beam is available
    boolean mAndroidBeamAvailable  = false;
    private ArrayList<String> myContacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupListView();
        // NFC isn't available on the device
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            /*
             * Disable NFC features here.
             * For example, disable menu items or buttons that activate
             * NFC-related features
             */
            // Android Beam file transfer isn't supported
        } else if (Build.VERSION.SDK_INT <
                Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // If Android Beam isn't available, don't continue.
            mAndroidBeamAvailable = false;
            /*
             * Disable Android Beam file transfer features here.
             */

            // Android Beam file transfer is available, continue
        } else {
            mAndroidBeamAvailable = true;
            mNfcAdapter = NfcAdapter.getDefaultAdapter(this);

        }
    }
    public void readFileIn(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput(fileName)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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
}