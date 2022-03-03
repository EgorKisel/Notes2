package com.geekbrains.notes2;

import java.io.Serializable;
import java.util.UUID;

public class NoteEntity implements Serializable {
    public final String id;
    public final String title;
    public final long date;
    public final String text;
    public final String phone;

    public NoteEntity (String id, String title, long date, String text, String phone){
        this.id = id;
        this.title = title;
        this.date = date;
        this.text = text;
        this.phone = phone;
    }
    public static String generateNewId(){
        return UUID.randomUUID().toString();
    }
}
