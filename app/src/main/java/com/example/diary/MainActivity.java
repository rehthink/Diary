package com.example.diary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private DiaryViewModel mDiaryViewModel;
    private Drawable icon;
    private  ColorDrawable background;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.constraintLayout);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TextView text = findViewById(R.id.empty);

        final DiaryListAdapter adapter = new DiaryListAdapter(new DiaryListAdapter.DiaryDiff());
        recyclerView.setAdapter(adapter);

        mDiaryViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DiaryViewModel.class);
        mDiaryViewModel.getAllDiary().observe(this, diary -> {
            // Update the cached copy of the words in the adapter.

            adapter.submitList(diary);
        });



        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView,
                                  RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped( RecyclerView.ViewHolder viewHolder,
                                 int direction) {

                background = new ColorDrawable(Color.RED);
                int position = viewHolder.getAdapterPosition();
                Diary diary = adapter.getDiaryPosition(position);
                mDiaryViewModel.delete(diary);

                Snackbar snackbar = Snackbar.make(constraintLayout, "Deleted", Snackbar.LENGTH_LONG);
                snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
                snackbar.setActionTextColor(Color.WHITE);
                snackbar.show();
            }

        });


        helper.attachToRecyclerView(recyclerView);

        FloatingActionButton floatingActionButton;
        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, entry.class);
                startActivity(intent);
            }
        });

    }

}
