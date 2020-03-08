package com.example.blob;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "t_table")
public class Transactions {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "amount")
    public String amount;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "desc")
    public String desc;

    /*public Transactions(int id, String amount, String type, String date) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.date = date;
    } */
    public Transactions(String amount, String type, String date, String desc) {
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.desc = desc;
    }

    public int getId() {return this.id;}
    public String getAmount() {return this.amount;}
    public String getType() {return this.type;}
    public String getDate() {return this.date;}
    public String getDesc() {return this.desc;}
}
