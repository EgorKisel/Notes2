package com.geekbrains.notes2;

import java.io.Serializable;
import java.util.Calendar;
import java.util.UUID;

public class NoteEntity implements Serializable {
    public final String id;
    public final String subject;
    public final long date;
    public final String text;

    public NoteEntity (String id, String subject, long date, String text){
        this.id = id;
        this.subject = subject;
        this.date = date;
        this.text = text;
    }
    public static String generateNewId(){
        return UUID.randomUUID().toString();
    }

    public static long getCurrentDate(){
        return Calendar.getInstance().getTimeInMillis();
    }
}
