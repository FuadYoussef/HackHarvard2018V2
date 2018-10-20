package com.jude.fuad.hackharvard2018v2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.nfc.NfcAdapter;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import java.io.File;

public class ShareNFCActivity extends AppCompatActivity {
    NfcAdapter mNfcAdapter;
    // Flag to indicate that Android Beam is available
    boolean mAndroidBeamAvailable  = false;
    PackageManager pm = this.getPackageManager();
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
            sendFile();
        }
    }
    public void sendFile() {
        mNfcAdapter = NfcAdapter.getDefaultAdapter(this);
        if(!mNfcAdapter.isEnabled()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enable NFC", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(new Intent(Settings.ACTION_NFC_SETTINGS));
        } else if (!mNfcAdapter.isNdefPushEnabled()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enable Android Beam", Toast.LENGTH_SHORT);
            toast.show();
            startActivity(new Intent(Settings.ACTION_NFCSHARING_SETTINGS));
        } else {
            String fileName = "userInfo";
            File extDir = getExternalFilesDir(null);
            File requestFile = new File(extDir, fileName);
            requestFile.setReadable(true, false);
            mNfcAdapter.setBeamPushUris(new Uri[]{Uri.fromFile(requestFile)}, this);
        }
    }

}
