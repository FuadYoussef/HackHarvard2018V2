package com.jude.fuad.hackharvard2018v2;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ShareQRActivity extends AppCompatActivity {
    private String sharingString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shareqr_activity);
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(openFileInput("userInfo")));
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                sharingString += "`" + line;
            }
        } catch (IOException e) {
            System.out.println("error reading or writing to file");
            e.printStackTrace();
        }

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
        ImageView imageView = findViewById(R.id.qrImage);
        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(sharingString, BarcodeFormat.QR_CODE, 200, 200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            imageView.setImageBitmap(bitmap);
        } catch (WriterException e) {
            e.printStackTrace();
        }

    }
}
