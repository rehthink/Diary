package com.example.diary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {

    private DiaryRepository mRepository;
    private final LiveData<List<Diary>> mAllDiary;

    public DiaryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new DiaryRepository(application);
        mAllDiary = mRepository.getAllDiary();
    }

    LiveData<List<Diary>> getAllDiary() { return mAllDiary; }

    public void insert(Diary diary) { mRepository.insert(diary); }
    public void delete(Diary diary) { mRepository.delete(diary); }


}
