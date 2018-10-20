package com.jude.fuad.hackharvard2018v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AddQRActivity extends AppCompatActivity {
    private String addedContact = "";
    private ArrayList<String> myContacts = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        integrator.setPrompt("Scan QR Code");
        integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.initiateScan();
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        //retrieve scan result
        //TextView tv = (TextView)findViewById(R.id.textView3);
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            //we have a result
            if(scanningResult.getContents() != null) {
                addedContact = scanningResult.getContents().toString();
                myContacts.add(addedContact);
                System.out.println(addedContact);
                System.out.println(addedContact);
                System.out.println(addedContact);
                try {
                    OutputStreamWriter writerTwo = new OutputStreamWriter(getApplicationContext().openFileOutput(
                            "contactFile", Context.MODE_PRIVATE));
                    for (int i = 0; i < myContacts.size(); i++) {
                        writerTwo.write(myContacts.get(i) + '\n');
                        System.out.println(myContacts.get(i) + '\n');
                    }
                    writerTwo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(myIntent);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        finish();
        startActivity(myIntent);
    }
}
