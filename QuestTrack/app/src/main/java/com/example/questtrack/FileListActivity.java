//package com.example.questtrack;
//
//import android.os.Bundle;
//import android.view.View;
//import android.widget.TextView;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.ArrayList;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//public class FileListActivity extends AppCompatActivity {
//
//    ArrayList<Note> notes = new ArrayList<>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_file_list);
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        TextView noFilesText = findViewById(R.id.noFilesFound);
//
//        ExecutorService executor = Executors.newSingleThreadExecutor();
//
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    notes = DataHandler.getAllNotes(FileListActivity.this);
//                } catch (IllegalAccessException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });
//
//        if (notes == null || notes.isEmpty()) {
//            noFilesText.setVisibility(View.VISIBLE);
//            return;
//        }
//
//        noFilesText.setVisibility(View.INVISIBLE);
//
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(new MyAdapter(getApplicationContext(), notes));
//    }
//
//}
