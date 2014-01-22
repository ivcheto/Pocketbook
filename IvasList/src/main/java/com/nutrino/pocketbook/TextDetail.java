package com.nutrino.pocketbook;

public class TextDetail extends NoteDetail {
    private String text;

    public TextDetail(String text){
        this.text = text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
