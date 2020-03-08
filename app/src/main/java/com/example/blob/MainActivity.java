package com.example.blob;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity {
    double saved;
    double spent;
    Button submitSaved;
    EditText savings;
    TextView currentSavings;
    AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find views for buttons and other elements
        submitSaved = findViewById(R.id.button);
        savings = findViewById(R.id.savings);
        currentSavings = findViewById(R.id.currentSavings);

        //get instance of the database
        //db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "transactions_db").build();
        db = AppDatabase.getInstance(this);
        //TODO: read data
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
//        Transactions t = new Transactions(2,4.5,"saved", date);
//        db.transactionsDao().insertTransaction(t);
        //db.transactionsDao().sumTransactions("saved");
        //File file = new File(this.getFilesDir(), "userdata.txt");
        //saved = 5.4;
    }

    public void addSaved(View v) { //triggered when you click the +savings button
        double toAdd = parseDouble(savings.getText().toString());
        saved += toAdd;
        currentSavings.setText("Current savings: " + saved);
    }
    public void addSpent(View v) { //triggered when you click the -spending button

    }

    public void saveData() { //write values to a text file
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
        saveData();
    }

    public void track(View v) { //temp method to go to Track activity
        Intent intent = new Intent(this, Track.class);
        startActivity(intent);
    }


}
