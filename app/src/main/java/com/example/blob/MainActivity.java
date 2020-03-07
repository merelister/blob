package com.example.blob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
    double savings;
    double spendings;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void writeData(View v) {
        String fileContents = savings + "," + spendings;
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput("studentdata.txt", Context.MODE_APPEND);
            outputStream.write(fileContents.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
//testing working with a different branch