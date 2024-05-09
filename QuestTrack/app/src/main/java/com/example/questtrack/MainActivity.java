package com.example.questtrack;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button logBtn = findViewById(R.id.newButton);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LogView.class);
                startActivity(intent);
            }
        });

        Button loadBtn = findViewById(R.id.loadButton);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FileListActivity.class);
                startActivity(intent);
            }
        });

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Note rayNote = new Note("Raymond Spitz");
                    rayNote.setBody("hello this is a note by raymond!");
                    DataHandler.SaveNote(MainActivity.this, rayNote);
                    Note quinnNote = new Note("Quinn Haywood");
                    quinnNote.setBody("hello this is a note by Quinton!");
                    DataHandler.SaveNote(MainActivity.this, quinnNote);
                    ArrayList<Note> noteArrayList = DataHandler.getListOfNotes(MainActivity.this);
                    for (Note note:
                         noteArrayList) {
                        Log.i("Note_Data", note.getNoteName() + " " + note.getBody());
                    }
                } catch (IllegalAccessException | IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}