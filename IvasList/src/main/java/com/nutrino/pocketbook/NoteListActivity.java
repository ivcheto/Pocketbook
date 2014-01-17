package com.nutrino.pocketbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ivayladermendjeva on 11/21/13.
 */
public class NoteListActivity extends Activity {
    private static final String LOG_TAG = NoteListActivity.class.getSimpleName();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);

        TextView listName = (TextView) findViewById(R.id.list_title);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString(getApplicationContext().getString(R.string.tag_list_title));
        listName.setText(title);
    }

    public void add(final View view) {
        Log.i(LOG_TAG, "create new note");

        Intent openNote = new Intent(this, NoteActivity.class);
        this.startActivity(openNote);
    }
}