package com.example.questtrack;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class DataHandler {

    public Note activeNote = null;


    public static void SaveNote(Context context, Note note) throws IOException {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            note.setDate(String.valueOf(LocalDateTime.now()));
        }
        String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = note.getNoteName() + ".txt";
        File directory = context.getFilesDir();
        File file = new File (directory, fileName);

        String fileContent = new Gson().toJson(note);
        FileOutputStream FOut =  context.openFileOutput(fileName, Context.MODE_PRIVATE);
        FOut.write(fileContent.getBytes());

        FOut.flush();
        FOut.close();

//        FileOutputStream fileout= context.openFileOutput(note.getNoteName() + ".txt", Context.MODE_PRIVATE);
//        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
//        outputWriter.write(fileContent);
//        outputWriter.close();

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
        for (Note note:
             listOfNotes) {
            if (note.getNoteName().equals(name)){
                return note;
            }
        }
        return null;
    }

    public Note getActiveNote(){
        if (this.activeNote != null){
            return this.activeNote;
        }
        return null;
    }

    public void setActiveNote(Note activeNote){
        this.activeNote = activeNote;
    }
}
