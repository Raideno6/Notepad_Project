package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NotepadMockup {
    public static void main(String[] args) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        File dir = new File("note");
        dir.mkdirs(); // Creates directory if it does not exist
        Scanner myScanner = new Scanner(System.in);
        while (true) {
            List<File> listOfNotes = List.of((dir.listFiles()));
            List<String> shortenedNoteNames = new ArrayList<>();

            // If notes exist, print them all
            if (!listOfNotes.isEmpty()) {
                for (int i = 0; i < listOfNotes.size(); i++) {
                    shortenedNoteNames.add(listOfNotes.get(i).getName().substring(0, listOfNotes.get(i).getName().length() - 5));
                }
            }

            System.out.println("\nWelcome to Notepad but Better\nDo you wanna create new note, delete a note, or load exisiting note? (new/delete/load)");
            String input = myScanner.nextLine();

            if (input.equalsIgnoreCase("new")) {
                // Formats date
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss-MM-dd-yyyy");

                System.out.println("\n");
                System.out.println("Enter in note name:");
                String noteName = myScanner.nextLine();

                boolean overwrite = true;
                // Checks if note name is taken and allows user to overwrite the note
                for (String shortenedNoteName : shortenedNoteNames) {
                    if (shortenedNoteName.equalsIgnoreCase(noteName)) {
                        System.out.println("Note already exists, do you want to overwrite it?");
                        String overwriteAns = myScanner.nextLine();
                        if (overwriteAns.equalsIgnoreCase("yes")) {
                            overwrite = true;
                        } else if (overwriteAns.equalsIgnoreCase("no")) {
                            overwrite = false;
                        }
                    }
                }
                if (!overwrite) {
                    continue;
                }

                // Creates file
                File jsonFile = new File(dir, (noteName + ".json"));
                FileWriter writer = new FileWriter(jsonFile);
                writer.flush();
                FileTime fileDate = (FileTime) Files.getAttribute(Path.of(jsonFile.getPath()), "creationTime");
                Note currentNote = new Note(noteName);
                String date = simpleDateFormat.format(fileDate.toMillis());
                currentNote.setDate(date);

                //region Set Note Parameters
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
                //endregion

                // Allows user to load and read a note
            } else if (input.equalsIgnoreCase("load")) {
                String notePath;
                try {
                    notePath = getFileLocation("Select note to open: ", myScanner, listOfNotes, shortenedNoteNames);
                } catch (Exception e) {
                    continue;
                }
                Reader reader = new FileReader(notePath);
                Note currentNote = gson.fromJson(reader, Note.class);
                currentNote.printNote();
            }
            // Asks if user wants to delete a note
            else if (input.equalsIgnoreCase("delete")) {
                String notePath;
                try {
                    notePath = getFileLocation("Select note to delete: ", myScanner, listOfNotes, shortenedNoteNames);
                } catch (Exception e) {
                    continue;
                }
                // Gets note file path and deletes it
                File deletedNote = new File(notePath);
                if (deletedNote.delete()) {
                    System.out.println(notePath.substring(20, notePath.length() - 5) + " was deleted");
                }
            } else {
                System.out.println("Invalid input");
            }
        }
    }

    /**
     * Displays list of files in a directory and prompts user to choose one
     *
     * @param prompt             Prompt for file manipulation
     * @param myScanner          Reads console for user selection
     * @param listOfNotes        List of all file names
     * @param shortenedNoteNames List of file names without path and file type
     * @return File path as string
     */
    public static String getFileLocation(String prompt, Scanner myScanner, List<File> listOfNotes, List<String> shortenedNoteNames) {
        int noteLocation = 0;
        while (true) {
            System.out.println(prompt);
            for (String shortenedNoteName : shortenedNoteNames) {
                System.out.println(shortenedNoteName);
            }
            String noteToChange = myScanner.nextLine();
            noteLocation = shortenedNoteNames.indexOf(noteToChange);
            if (noteLocation == -1) {
                System.out.println("Note not found");
                continue;
            }
            break;
        }
        return String.valueOf(listOfNotes.get(noteLocation));
    }
}