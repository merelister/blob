package com.example.blob;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
public static final String EXTRA_REPLY =
        "com.example.android.blob.REPLY";

    private  EditText mAmount;
    private EditText mDesc;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track);
        mAmount = findViewById(R.id.amount);
        mDesc = findViewById(R.id.desc);

        final Button button = findViewById(R.id.save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mAmount.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String amount = mAmount.getText().toString();
                    String desc = mDesc.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY,amount);
                    //replyIntent.putExtra("amount", amount);
                    replyIntent.putExtra("desc", desc);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
