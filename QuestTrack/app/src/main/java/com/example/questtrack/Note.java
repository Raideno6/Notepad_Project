package com.example.questtrack;





public class Note {

    private String author;

    private String date;

    private String noteName;

    private String body;

    private String subtitle;

    public Note(String noteName) {
        this.noteName = noteName;
    }

    /**
     * Prints note out in correct format
     */
    public void printNote() {
        System.out.printf("%s\n %s %s \n\n%15s \n%s \n%s\n\n", " Title: " + noteName, "By: " + author, "On: " + date, subtitle, "=================================", body);
    }
}
