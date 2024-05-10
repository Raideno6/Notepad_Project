package com.example.questtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

// androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LogView extends AppCompatActivity {

    Note currentNote = new Note("new note");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //EdgeToEdge.enable(this);
        setContentView(R.layout.activity_log_view);

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            try {
                Note note = DataHandler.getNote(getIntent().getStringExtra("note"), LogView.this);
                if (note != null) {
                    currentNote = note;
                }
                EditText noteName = findViewById(R.id.noteName);
                noteName.setText(currentNote.getNoteName());
                EditText noteBody = findViewById(R.id.noteBody);
                noteBody.setText(currentNote.getBody());
            } catch (FileNotFoundException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button saveBtn = findViewById(R.id.saveButton);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }
}