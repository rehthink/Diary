package com.example.diary;

import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.io.ObjectInputStream;

public class DiaryListAdapter extends ListAdapter<Diary, DiaryViewHolder> {

    public DiaryListAdapter(@NonNull DiffUtil.ItemCallback<Diary> diffCallBack) {
        super(diffCallBack);
    }

    @Override
    public DiaryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DiaryViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(DiaryViewHolder holder, int position) {
        Diary current = getItem(position);
        holder.bind(current.getDiary());
    }

    public Diary getDiaryPosition(int position) {

        return getItem(position);
    }


    static class DiaryDiff extends DiffUtil.ItemCallback<Diary> {

        @Override
        public boolean areItemsTheSame(@NonNull Diary oldItem, @NonNull Diary newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Diary oldItem, @NonNull Diary newItem) {
            return oldItem.getDiary().equals(newItem.getDiary());
        }

    }

}
