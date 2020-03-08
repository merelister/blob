package com.example.blob;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.lang.Double.parseDouble;

public class Track extends AppCompatActivity {
//this is where the user inputs new saving/spendings
    EditText saved, spent;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);

        getIntent();

        saved = findViewById(R.id.saved);
        spent = findViewById(R.id.spent);
        save = findViewById(R.id.save);
    }

    public void saveData(View v) { //onClick of Save button
        double amtSaved = parseDouble(saved.getText().toString());
        double amtSpent = parseDouble(spent.getText().toString());
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        //TODO: write data to MySQLite

        Toast.makeText(this, "saved: " + amtSaved + "\nspent: " + amtSpent + "\ndate: " + date, Toast.LENGTH_LONG).show();
    }
}
