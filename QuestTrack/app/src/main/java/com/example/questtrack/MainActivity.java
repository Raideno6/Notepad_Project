package com.example.questtrack;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
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

        Button newBtn = findViewById(R.id.newButton);

        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, LogView.class);
                startActivity(intent);
            }
        });

        Button loadBtn = findViewById(R.id.loadButton);

        loadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
<<<<<<< HEAD
            public void onClick(View v) {
                //permission allowed
                Intent intent = new Intent(MainActivity.this, FileListActivity.class);
                startActivity(intent);
=======
            public void run() {
                try {
                    Note note = new Note("Hello");
                    note.setBody("Hi!");
                    DataHandler.SaveNote(MainActivity.this, note);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
>>>>>>> 825b64b732bedb14e09895d31651fb23278aa716
            }
        });

    }

}