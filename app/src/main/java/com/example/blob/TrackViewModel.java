package com.example.blob;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TrackViewModel extends AndroidViewModel {

    private TRepository tRepo;
    private LiveData<List<Transactions>> allTransactions;

    public TrackViewModel (Application application) {
        super(application);
        tRepo = new TRepository(application);
        allTransactions = tRepo.getAll();
    }
    LiveData<List<Transactions>> getAllTransactions() {return allTransactions;}
    public void insert(Transactions t) {tRepo.insert(t);}
}
