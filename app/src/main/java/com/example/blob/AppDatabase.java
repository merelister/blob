package com.example.blob;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Database(entities = {Transactions.class}, version = 3, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String DB_NAME = "transactions_db";
    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    //create db here
                    instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                            //wipes and rebuilds instead of migrating
                            //if no Migration objects
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return instance;
    }
    /*public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }*/
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(instance).execute();
                }
            };
    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final TransactionsDao mDao;
        //String[] amounts = {"$1.52", "$2.80", "$3.11", "$8.46"};

        PopulateDbAsync(AppDatabase db) {
            mDao = db.transactionsDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            mDao.deleteAll();
            String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
           /* for (int i = 0; i <= amounts.length - 1; i++) {
                Transactions t = new Transactions(amounts[i],"spent", date, "test description");
                mDao.insert(t);
            } */
           Transactions t = new Transactions("$2.30","spent",date, "candy");
           mDao.insert(t);
           Transactions m = new Transactions("$10.00","saved",date, "allowance");
           mDao.insert(m);
           Transactions n = new Transactions("$5.99","spent",date, "lunch");
           mDao.insert(n);
            return null;
        }
    }

    public abstract TransactionsDao transactionsDao();
}
