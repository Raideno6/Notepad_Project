package com.example.questtrack;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FileListActivity extends AppCompatActivity {

    ArrayList<Note> notes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_list);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        TextView noFilesText = findViewById(R.id.noFilesFound);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    notes = DataHandler.getListOfNotes(FileListActivity.this);

                    for (Note note: notes) {
                        Log.i("Note_Data_2", note.getNoteName() + " " + note.getBody());
                    }

                    if (notes == null || notes.isEmpty()) {
                        noFilesText.setVisibility(View.VISIBLE);
                        return;
                    }

                    noFilesText.setVisibility(View.INVISIBLE);


                } catch (IllegalAccessException | FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(FileListActivity.this));
        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), notes));

    }

}
