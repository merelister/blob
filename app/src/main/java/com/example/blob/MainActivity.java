package com.example.blob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {
/*this is the home screen when opening the app. it shows the blob character(s) and
buttons to add money to savings or track spending
 */
double saved;
double spent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: find views for buttons and other elements

        //TODO: read data
        File file = new File(this.getFilesDir(), "userdata.txt");
    }

    public void addSaved(View v) { //triggered when you click the +savings button

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
            Toast.makeText(this, "Could not save data", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //TODO: write data
    }
}
//testing working with a different branch