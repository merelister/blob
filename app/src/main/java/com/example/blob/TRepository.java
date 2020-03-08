package com.example.blob;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TRepository {

    private TransactionsDao transactionsDao;
    private LiveData<List<Transactions>> allTransactions;

    TRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        transactionsDao = db.transactionsDao();
        allTransactions = transactionsDao.getAll();
    }
    LiveData<List<Transactions>> getAll() {
        return allTransactions;
    }
    public void insert (Transactions t) {
        new insertAsyncTask(transactionsDao).execute(t);
    }
    private static class insertAsyncTask extends AsyncTask<Transactions, Void, Void> {

        private TransactionsDao mAsyncTaskDao;

        insertAsyncTask(TransactionsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transactions... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
