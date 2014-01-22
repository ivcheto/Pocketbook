package com.nutrino.pocketbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Vector;


public class NoteActivity extends Activity {

    Note note;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // get the damn layout
        setContentView(R.layout.note);
    }

    // put some shit here to save shit
    public void save(final View view) {
        final TextView titleField = (TextView) this.findViewById(R.id.note_title_field);
        final TextView bodyField = (TextView) this.findViewById(R.id.note_body_field);

        final String title = titleField.getText().toString();
        final String body =  bodyField.getText().toString();

        // once the logic to pull notes from data source is implemented,
        // move this to onCreate
        if(this.note == null) {
            this.note = new Note();
        }

        this.note.setTitle(title);
        Vector<NoteDetail> bodyParts = new Vector<NoteDetail>();
        bodyParts.add(new TextDetail(body));
        this.note.setNoteDetails(bodyParts);

        saveToDB();

        finish();
    }

    public void saveToDB(){

    }

    // put some shit here to get shit

    //
}