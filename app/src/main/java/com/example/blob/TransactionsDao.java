package com.example.blob;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TransactionsDao {
    //example queries
    @Query("SELECT * FROM transactions")
    List<Transactions> getAll();

    @Query("SELECT * FROM transactions WHERE id IN (:ids)")
    List<Transactions> loadAllByIds(int[] ids);

    @Query("SELECT * FROM Transactions WHERE type LIKE :type LIMIT 1")
    Transactions findByName(String type);

    @Insert
    void insertAll(Transactions... transactions);

    @Delete
    void delete(Transactions transactions);

}
