package com.example.blob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Goal extends AppCompatActivity
implements getGoal.OnFragmentInteractionListener{
    //This page will show your goal at the top and your transaction history
    private TrackViewModel TVM;
    TextView txt;
    Button addSavings;
    Button addSpending;

    public static final int NEW_ENTRY_REQUEST_CODE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        //getIntent();
        getGoal.newInstance("500", "200");
        onFragmentInteraction("test string");
        //Fragment fragment = getGoal.OnFragmentInteractionListener();
        //TODO: display goal at top of page

        RecyclerView recyclerView = findViewById(R.id.recyclerview); //transaction history
        final TListAdaptor adaptor = new TListAdaptor(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addSavings = findViewById(R.id.goalSavings);
        addSpending = findViewById(R.id.goalSpend);

        TVM = ViewModelProviders.of(this).get(TrackViewModel.class);

        TVM.getAllTransactions().observe(this, new Observer<List<Transactions>>(){
            @Override
            public void onChanged(@Nullable final List<Transactions> t) {
                // Update the cached copy of the Transactions in the adapter.
                adaptor.setTransactions(t);
            }
        });

        //txt = findViewById(R.id.textView);
        /*String sum;
        sum = TVM.sumTransactions("spent");
        //txt.setText(sum);
        //txt.setText(TVM.sumTransactions("spent")); //total spent
        */



    }
    public void onFragmentInteraction(String test) {
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
    }
    public void addTransaction(View v) {
        Intent intent = new Intent(this, Track.class);
        startActivityForResult(intent,NEW_ENTRY_REQUEST_CODE );
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
        String amt = data.getStringExtra(Track.EXTRA_REPLY);
        String desc = data.getStringExtra("desc");
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Transactions t = new Transactions(amt,"spent",date,desc);
            TVM.insert(t);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "not saved",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
