package com.example.blob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity {
    double saved;
    double spent;
    Button submitSaved;
    EditText savings;
    TextView currentSavings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //TODO: find views for buttons and other elements
        submitSaved = findViewById(R.id.button);
        savings = findViewById(R.id.savings);
        currentSavings = findViewById(R.id.currentSavings);

        //TODO: read data
        File file = new File(this.getFilesDir(), "userdata.txt");
        saved = 5.4;
    }

    public void addSaved(View v) { //triggered when you click the +savings button
        double toAdd = parseDouble(savings.getText().toString());
        saved += toAdd;
        currentSavings.setText("Current savings: " + saved);
    }
    public void addSpent(View v) { //triggered when you click the -spending button

    }

    public void saveData(View v) { //write values to a text file
        String toWrite = saved + "," + spent;
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("userdata.txt", Context.MODE_APPEND);
            outputStream.write(toWrite.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO: write data

    }


}
