package com.example.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryViewHolder extends RecyclerView.ViewHolder {

    private final TextView diaryItemView;

    private DiaryViewHolder(View itemView) {
        super(itemView);
        diaryItemView = itemView.findViewById(R.id.text);
    }

    public void bind(String text) {
       diaryItemView.setText(text);
    }

    static DiaryViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main, parent, false);
        return new DiaryViewHolder(view);
    }
}
