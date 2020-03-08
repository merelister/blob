package com.example.blob;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TransactionsDao {
    //example queries
    @Query("SELECT * FROM t_table ORDER BY date DESC") //select all transactions
    LiveData<List<Transactions>> getAll();

    @Query("SELECT * FROM t_table WHERE id IN (:ids)")
    List<Transactions> loadAllByIds(int[] ids);

    @Query("SELECT * FROM t_table WHERE type LIKE :type ORDER BY date DESC")
    LiveData<List<Transactions>> getAllType(String type);

    @Insert
    void insert(Transactions t);

    @Delete
    void deleteTransaction(Transactions t);

    @Update
    void updateTransaction(Transactions t);

    @Query("DELETE FROM t_table")
    void deleteAll();

    //sum spending/saving transactions
    @Query("SELECT sum(amount) FROM t_table WHERE type= :type GROUP BY type")
    String sumTransactions(String type);

}
