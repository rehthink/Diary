package com.example.diary;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diary_table")
public class Diary {

    @PrimaryKey(autoGenerate = true) private int id = 0;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @ColumnInfo(name = "diary") private String mDiary;
    public Diary(@NonNull String diary) {this.mDiary = diary;}
    public String getDiary() {
        return mDiary;
    }

}
