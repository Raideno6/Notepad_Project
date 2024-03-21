package org.example;

public class Note {
    private String author;
    private String date;
    private String noteName;
    private String body;
    private String subtitle;

    public Note(String noteName){
        this.noteName = noteName;
    }

    public void setAuthor(String author){
        this.author = author;
    }

    public String getAuthor(){
        return author;
    }

    public void setBody(String body){
        this.body = body;
    }

    public String getBody(){
        return body;
    }

    public void setSubtitle(String subtitle){
        this.subtitle = subtitle;
    }

    public String getSubtitle(){
        return subtitle;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getDate(){
        return date;
    }

    public String getNoteName(){
        return noteName;
    }

    public void printNote(){
        System.out.printf("%s\n %s %s \n\n%15s \n%s \n%s\n\n", " Title: " + noteName, "By: " + author, "On: " + date, subtitle,"=================================", body);
    }
}
