package com.example.blob;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionsDao {
    //example queries
    @Query("SELECT * FROM transactions ORDER BY date DESC") //select all transactions
    List<Transactions> getAll();

    @Query("SELECT * FROM transactions WHERE id IN (:ids)")
    List<Transactions> loadAllByIds(int[] ids);

    @Query("SELECT * FROM transactions WHERE type LIKE :type LIMIT 1")
    Transactions findByName(String type);

    @Insert
    void insertTransaction(Transactions transactions);

    @Delete
    void deleteTransaction(Transactions transactions);

    @Update
    void updateTransaction(Transactions transactions);

    //sum spending/saving transactions
    @Query("SELECT sum(amount) FROM transactions WHERE type= :type GROUP BY type")
    String sumTransactions(String type);

}
