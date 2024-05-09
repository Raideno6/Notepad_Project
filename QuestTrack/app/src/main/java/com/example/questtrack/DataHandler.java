package com.example.questtrack;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DataHandler {

    public static void SaveNote(Context context, Note note) throws IOException {
        File directory = new File("/data/data/com.example.questtrack/files/Notes");
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

    public static ArrayList<Note> getAllNotes(Context context) throws IllegalAccessException {
        String[] files = context.fileList();

        ArrayList<Note> test = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        for(int i=0; i < fields.length; i++){
            Log.i("Raw Asset ", fields[i].getName());
            int resourceID=fields[i].getInt(fields[i]);
            test.add(convertJsonToObject(context, resourceID));
        }
        return test;
    }
}
