package com.example.questtrack;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataHandler {

    public static void SaveNote(Context context, Note note) throws IOException {
        File directory = new File("data/data/com.example.questtrack/files/Notes");
        File file = new File(directory, note.getNoteName());
        String fileContent = new Gson().toJson(note);
        byte[] byteArrray = fileContent.getBytes();
        try (FileOutputStream fos = context.openFileOutput(file.getName(), Context.MODE_PRIVATE)) {
            fos.write(byteArrray);
        }
    }


    public static Note convertJsonToObject(Context context, int rawID) {

        InputStream inputStream = context.getResources().openRawResource(rawID);
        String jsonString = "";
        try {
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();

            jsonString = new String(data, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return new Gson().fromJson(jsonString, new TypeToken<Note>(){}.getType());
    }

    public static ArrayList<Note> getListOfNotes(Context context) throws IllegalAccessException, FileNotFoundException {
        ArrayList<Note> noteArrayList = new ArrayList<>();
        InputStream myInputStream;
        Gson gson = new Gson();
        for (String file:
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
            JsonObject json = JsonParser.parseString(contents).getAsJsonObject();
            Note note = gson.fromJson(json, Note.class);
            noteArrayList.add(note);
        }
        return noteArrayList;
    }
}
