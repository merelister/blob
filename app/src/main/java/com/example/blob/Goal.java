package com.example.blob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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
    Button add;
    public static final int NEW_ENTRY_REQUEST_CODE = 1;
    String type;
    //fragment gg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        getGoal.newInstance("500", "200"); //test values
        onFragmentInteraction("onFragInteract");

        //TODO: display goal at top of page

        RecyclerView recyclerView = findViewById(R.id.recyclerview); //transaction history
        final TListAdaptor adaptor = new TListAdaptor(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add = findViewById(R.id.button2);

        TVM = ViewModelProviders.of(this).get(TrackViewModel.class);

        TVM.getAllTransactions().observe(this, new Observer<List<Transactions>>(){
            @Override
            public void onChanged(@Nullable final List<Transactions> t) {
                // Update the cached copy of the Transactions in the adapter.
                adaptor.setTransactions(t);
            }
        });
    }
    public void onFragmentInteraction(String test) {
        Toast.makeText(this, test, Toast.LENGTH_SHORT).show();
    }

    //add transaction (open Track Activity, pass data back to this class then insert)
    public void addTransaction(View v) {
        Intent intent = new Intent(this, Track.class);
        startActivityForResult(intent,NEW_ENTRY_REQUEST_CODE );
    }
    //add spending
    public void addSpending(View v) {
        Intent intent = new Intent(this, Track.class);
        type = "spent";
        startActivityForResult(intent,NEW_ENTRY_REQUEST_CODE );
    }
    //add savingss
    public void addSaving(View v) {
        Intent intent = new Intent(this, Track.class);
        type = "saved";
        startActivityForResult(intent, NEW_ENTRY_REQUEST_CODE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_ENTRY_REQUEST_CODE && resultCode == RESULT_OK) {
        String amt = data.getStringExtra(Track.EXTRA_REPLY);
        String desc = data.getStringExtra("desc");
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        Transactions t = new Transactions(amt,type,date,desc);
            TVM.insert(t);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "not saved",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
