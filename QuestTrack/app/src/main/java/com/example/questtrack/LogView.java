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
import java.io.IOException;
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
            EditText nameField = findViewById(R.id.noteName);
            EditText bodyField = findViewById(R.id.noteBody);
            try {
                Note note = DataHandler.getNote(getIntent().getStringExtra("note"), LogView.this);
                if (note != null) {
                    currentNote = note;
                }
                nameField.setText(currentNote.getNoteName());
                bodyField.setText(currentNote.getBody());
            } catch (FileNotFoundException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

            Button saveBtn = findViewById(R.id.saveButton);

            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Note tempNote = new Note(nameField.getText().toString());
                    tempNote.setBody(bodyField.getText().toString());
                    try {
                        DataHandler.SaveNote(LogView.this, tempNote);
                        Intent intent = new Intent(LogView.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                }
            });
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.logScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




    }
}