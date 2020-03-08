package com.example.blob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.lang.Double.parseDouble;


public class MainActivity extends AppCompatActivity {
    Button gotoGoal; //this button is temporary (will be app menu bar)
    private TrackViewModel TVM; //testing

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //find views for buttons and other elements
        gotoGoal = findViewById(R.id.goal);

        //TODO: query db for total saved and spent

        //TODO: add button to input data

        //testing - can the main activity access the db?
        final TListAdaptor adaptor = new TListAdaptor(this);
        RecyclerView rview = findViewById(R.id.rview);
        rview.setAdapter(adaptor);
        rview.setLayoutManager(new LinearLayoutManager(this));

        TVM = ViewModelProviders.of(this).get(TrackViewModel.class);
        TVM.getAllTransactions().observe(this, new Observer<List<Transactions>>(){
            @Override
            public void onChanged(@Nullable final List<Transactions> t) {
                // Update the cached copy of the Transactions in the adapter.
                adaptor.setTransactions(t);
            }
        });
    }

    public void goal(View v) { //temp method to go to Goal activity
        Intent intent = new Intent(this, Goal.class);
        startActivity(intent);
    }
    /* public void addSaved(View v) { //triggered when you click the +savings button
        double toAdd;
        if(savings.getText().toString() != null) {
            toAdd = parseDouble(savings.getText().toString());
            saved += toAdd;
        }
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
    */





}
