package org.example;

import lombok.Getter;
import lombok.Setter;

public class Note {
    @Getter
    @Setter
    private String author;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String noteName;
    @Getter
    @Setter
    private String body;
    @Getter
    @Setter
    private String subtitle;

    public Note(String noteName) {
        this.noteName = noteName;
    }

    public void printNote() {
        System.out.printf("%s\n %s %s \n\n%15s \n%s \n%s\n\n", " Title: " + noteName, "By: " + author, "On: " + date, subtitle, "=================================", body);
    }
}
