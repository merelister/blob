package com.example.blob;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TRepository {

    private TransactionsDao transactionsDao;
    private LiveData<List<Transactions>> allTransactions;
    private String sumSpent;
    private String sumSaved;

    TRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        transactionsDao = db.transactionsDao();
        allTransactions = transactionsDao.getAll();
        sumSpent = transactionsDao.sumTransactions("spent");
        sumSaved = transactionsDao.sumTransactions("saved");
    }
    LiveData<List<Transactions>> getAll() {
        return allTransactions;
    }
    public void insert (Transactions t) {
        new insertAsyncTask(transactionsDao).execute(t);
    }
    /*public String sumTransactions (String type) {
        if (type.equals("spent"))
            return sumSpent;
        else if (type.equals("saved"))
            return sumSaved;
        else return "oops error";
    } */
    public String sumTransactions() {new sumAsyncTask(transactionsDao).execute("spent");}
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
    private static class sumAsyncTask extends AsyncTask<Transactions, Void, Void> {

        private TransactionsDao mAsyncTaskDao;

        sumAsyncTask(TransactionsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Transactions... params) {
            mAsyncTaskDao.sumTransactions("spent");
            return null;
        }
    }
}

