package com.nutrino.pocketbook;

import java.util.Vector;

/**
 * Created by ivayladermendjeva on 12/11/13.
 */
public class Note {

    private String name;
    private Vector<NoteDetail> noteDetails;

    public Note(String name){
        this.name = name;
        noteDetails = new Vector<NoteDetail>();
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return getName();
    }
}
