package com.nutrino.pocketbook;

import java.util.Vector;


public class Note {

    private String title;
    private Vector<NoteDetail> noteDetails;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setNoteDetails(Vector<NoteDetail> details) {
        this.noteDetails = details;
    }

    public String toString() {
        return getTitle();
    }
}
