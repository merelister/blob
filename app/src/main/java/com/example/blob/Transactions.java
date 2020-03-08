package com.example.blob;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Transactions {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "amount")
    public String amount;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "date")
    public String data;
}
