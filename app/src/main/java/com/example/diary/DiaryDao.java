package com.example.diary;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiaryDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Diary diary);

    @Delete
    void delete(Diary diary);

    @Query("SELECT * FROM diary_table order by id ASC")
    LiveData<List<Diary>> getDiary();
}
