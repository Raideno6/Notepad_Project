package org.example;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;


public class TextMockup {
    public static void main(String[] args) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dir = new File("note");
        dir.mkdirs();
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Welcome to Notepad but Better\nDo you wanna create new note or load exisiting note? (new/load)");
        String input = myScanner.nextLine();
        while (true){
            if (input.equalsIgnoreCase("new")){
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss-MM-dd-yyyy");
                String date = simpleDateFormat.format(new Date());
                System.out.println("\n\n\n");
                System.out.println("Enter in note name:");
                String noteName = myScanner.nextLine();
                File jsonFile = new File(dir, (noteName + ".json"));
                FileWriter writer = new FileWriter(jsonFile);
                Note currentNote = new Note(noteName);
                currentNote.setDate(date);
                System.out.println("\nEnter author name:");

                String authorName = myScanner.nextLine();
                currentNote.setAuthor(authorName);

                System.out.println("\nEnter subtitle:");

                String subtitle = myScanner.nextLine();
                currentNote.setSubtitle(subtitle);

                System.out.println("\nEnter body text:");

                String body = myScanner.nextLine();
                currentNote.setBody(body);

                gson.toJson(currentNote, writer);

                writer.close();
                continue;
            }
            if (input.equalsIgnoreCase("load")){
                while (true){
                    System.out.println("Choose a note to open:");
                    List<File> listOfNotes = List.of(dir.listFiles());
                    List<String> shortenedNoteNames = new ArrayList<String>();
                    for (int i = 0; i < listOfNotes.size(); i++) {
                        shortenedNoteNames.add(listOfNotes.get(i).getName().substring(0, listOfNotes.get(i).getName().length() - 5));
                        System.out.println(shortenedNoteNames.get(i));
                    }
                    String noteToLoad = myScanner.nextLine();
                    int noteLocation = shortenedNoteNames.indexOf(noteToLoad);
                    if(noteLocation == -1){
                        System.out.println("Note not found");
                        continue;
                    }
                    Reader reader = new FileReader(listOfNotes.get(noteLocation));
                    Note currentNote = gson.fromJson(reader, Note.class);
                    currentNote.printNote();
                    break;

                }
                break;

            }
        }
    }
}