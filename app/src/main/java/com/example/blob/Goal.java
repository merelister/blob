package com.example.blob;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class Goal extends AppCompatActivity {
    private TrackViewModel TVM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        getIntent();

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TListAdaptor adaptor = new TListAdaptor(this);
        recyclerView.setAdapter(adaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TVM = ViewModelProviders.of(this).get(TrackViewModel.class);

        TVM.getAllTransactions().observe(this, new Observer<List<Transactions>>(){
            @Override
            public void onChanged(@Nullable final List<Transactions> t) {
                // Update the cached copy of the words in the adapter.
                //TListAdaptor adaptor = new TListAdaptor();
                adaptor.setTransactions(t);
            }
        });

    }
}
