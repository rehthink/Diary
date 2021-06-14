package com.example.diary;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;

public class entry extends AppCompatActivity {
    private DiaryViewModel mDiaryViewModel;
    private ConstraintLayout constraintLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_entry);
        constraintLayout = findViewById(R.id.layoutt);

     mDiaryViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication())).get(DiaryViewModel.class);

        Button submit = findViewById(R.id.button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitData(view);
            }
        });

    }

    public void submitData(View view) {
        EditText input = findViewById(R.id.input);
       String diary = input.getText().toString();
        mDiaryViewModel.insert(new Diary(diary));
        Intent intent = new Intent(getBaseContext(), MainActivity.class);
        intent.putExtra("Diary", diary);


        Snackbar snackbar = Snackbar.make(constraintLayout, "Note Added.", Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.purple_500));
        snackbar.setActionTextColor(Color.WHITE);
        snackbar.show();

        startActivity(intent);
        input.getText().clear();

    }
}