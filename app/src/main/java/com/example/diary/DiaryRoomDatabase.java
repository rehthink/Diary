package com.example.diary;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Diary.class}, version = 1, exportSchema = false)
public abstract class DiaryRoomDatabase extends RoomDatabase {

    public abstract DiaryDao diaryDao();
    private static volatile DiaryRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static DiaryRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DiaryRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DiaryRoomDatabase.class, "diary_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

   /* private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                DiaryDao dao = INSTANCE.diaryDao();
                dao.deleteAll();

                Diary diary = new Diary("Hello");
                dao.insert(diary);
                diary = new Diary("World");
                dao.insert(diary);
            });
        }
    };*/
}
