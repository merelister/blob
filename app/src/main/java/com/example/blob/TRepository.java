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
//        sumSpent = transactionsDao.sumTransactions("spent");
        //sumSaved = transactionsDao.sumTransactions("saved");
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
    public String sumTransactions(String type) {
       try {
           return new sumAsyncTask(transactionsDao).execute().get();
       } catch (Exception e) {}
        //return final Result get ()
        //return
        return "";
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
    private static class sumAsyncTask extends AsyncTask<Transactions, Void, String> {

        private TransactionsDao mAsyncTaskDao;

        sumAsyncTask(TransactionsDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected String doInBackground(final Transactions...type) {
            String sum = mAsyncTaskDao.sumTransactions("spent");
            return sum;
        }
        protected void onPostExecute(String result) {}
    }
}

