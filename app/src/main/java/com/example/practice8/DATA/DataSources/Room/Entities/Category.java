package com.example.practice8.DATA.DataSources.Room.Entities;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "itemsName")
    public String itemsName;
    @ColumnInfo(name = "img")
    public int img;
}