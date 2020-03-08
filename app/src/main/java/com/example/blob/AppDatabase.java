package com.example.blob;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Transactions.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TransactionsDao transactionsDao();
}
