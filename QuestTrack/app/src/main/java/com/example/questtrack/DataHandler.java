package com.example.questtrack;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class DataHandler {


    public static Note convertJsonToObject(Context context) {

        InputStream inputStream = context.getResources().openRawResource(R.raw.note);
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

    public static ArrayList<Note> getAllNotes(){
        ArrayList<Note> test = new ArrayList<>();
        test.add(new Note("Hello!"));
        Field[] fields = R.raw.class.getFields();
        for(int i=0; i < fields.length; i++){
            Log.i("Raw Asset ", fields[i].getName());
        }
        return test;
    }
}
