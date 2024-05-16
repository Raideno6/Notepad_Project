package com.example.questtrack;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataHandler {

    public static void SaveNote(Context context, Note note) throws IOException {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            note.setDate(String.valueOf(LocalDateTime.now()));
        }
        String fileName = note.getNoteName() + ".txt";

        String fileContent = new Gson().toJson(note);
        FileOutputStream FOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
        FOut.write(fileContent.getBytes());

        FOut.flush();
        FOut.close();


    }


    public static ArrayList<Note> getListOfNotes(Context context) throws IllegalAccessException, FileNotFoundException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        Gson gson = new Gson();
        for (String file :
                context.fileList()) {
            File noteToBe = new File(context.getFilesDir(), file);
            FileInputStream fis = context.openFileInput(noteToBe.getName());
            InputStreamReader inputStreamReader =
                    new InputStreamReader(fis, StandardCharsets.UTF_8);
            StringBuilder stringBuilder = new StringBuilder();
            String contents;
            try (BufferedReader reader = new BufferedReader(inputStreamReader)) {
                String line = reader.readLine();
                while (line != null) {
                    stringBuilder.append(line).append('\n');
                    line = reader.readLine();
                }
            } catch (IOException e) {
                // Error occurred when opening raw file for reading.
            } finally {
                contents = stringBuilder.toString();
            }
            Log.i("File_data", contents);
            JsonObject json = null;
            try {
                json = JsonParser.parseString(contents).getAsJsonObject();
                Note note = gson.fromJson(json, Note.class);
                noteArrayList.add(note);
            } catch (Exception e) {
                Log.i("Error", String.valueOf(e));
            }

        }
        return noteArrayList;
    }

    public static Note getNote(String name, Context context) throws FileNotFoundException, IllegalAccessException {
        ArrayList<Note> listOfNotes = getListOfNotes(context);
        for (Note note :
                listOfNotes) {
            if (note.getNoteName().equals(name)) {
                return note;
            }
        }
        return null;
    }
}

