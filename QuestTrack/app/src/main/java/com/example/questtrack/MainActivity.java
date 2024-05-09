package com.example.questtrack;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
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
                    Note note = new Note("Hello");
                    note.setBody("Hi!");
                    DataHandler.SaveNote(MainActivity.this, note);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}