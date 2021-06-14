package com.example.diary;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DiaryRepository {
        private DiaryDao mDiaryDao;
        private LiveData<List<Diary>> mAllDiary;


        DiaryRepository(Application application) {
            DiaryRoomDatabase db = DiaryRoomDatabase.getDatabase(application);
            mDiaryDao = db.diaryDao();
            mAllDiary = mDiaryDao.getDiary();
        }

        LiveData<List<Diary>> getAllDiary(){
            return mAllDiary;
        }

    void insert(Diary diary) {
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDiaryDao.insert(diary);
        });
        }

    void delete(Diary diary) {
        DiaryRoomDatabase.databaseWriteExecutor.execute(() -> {
            mDiaryDao.delete(diary);
        });
    }


}
