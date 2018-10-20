package com.jude.fuad.hackharvard2018v2;

import android.content.pm.PackageManager;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class ShareNFCActivity extends AppCompatActivity {
    NfcAdapter mNfcAdapter;
    // Flag to indicate that Android Beam is available
    boolean mAndroidBeamAvailable  = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharingnfc_activity);
        // NFC isn't available on the device
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_NFC)) {
            /*
             * Disable NFC features here.
             * For example, disable menu items or buttons that activate
             * NFC-related features
             */

            Toast toast = Toast.makeText(getApplicationContext(),
                    "NFC not available on this device", Toast.LENGTH_SHORT);
            toast.show();
            // Android Beam file transfer isn't supported
        } else if (Build.VERSION.SDK_INT <
                Build.VERSION_CODES.JELLY_BEAN_MR1) {
            // If Android Beam isn't available, don't continue.
            mAndroidBeamAvailable = false;
            /*
             * Disable Android Beam file transfer features here.
             */
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Android Beam not available on this device", Toast.LENGTH_SHORT);
            toast.show();
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
}
