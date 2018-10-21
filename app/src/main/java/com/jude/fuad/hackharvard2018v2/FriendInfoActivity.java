package com.jude.fuad.hackharvard2018v2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class FriendInfoActivity extends AppCompatActivity {
    private ArrayList<String> myContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friendinfo_activity);
        setupListView();
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
        String ls = myContacts.get(MainActivity.friendIndex);
        String[] dataSplit = ls.split("`");
        String[] valSplit = dataSplit[1].split("~");
        TextView tv = findViewById(R.id.nameText);
        tv.setText(valSplit[1]);
        ArrayList<String> al = new ArrayList<>();
        for(int i = 2; i < dataSplit.length; i++) {
            String[] vs = dataSplit[i].split("~");
            al.add(vs[0] + ": " + vs[1]);
        }
        String[] myContactsNames = new String[al.size()];
        for (int i = 0; i < al.size(); i++) {
            myContactsNames[i] = al.get(i);
        }
        ListView listDays = findViewById(R.id.infoList);
        ArrayAdapter<String> arrayAdapter =
                new ArrayAdapter<String>(this,
                        android.R.layout.simple_list_item_1, myContactsNames);
        // Set The Adapter
        listDays.setAdapter(arrayAdapter);
    }
}
