package com.nutrino.pocketbook;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by ivayladermendjeva on 11/21/13.
 */
public class NotesActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.items_list);

        TextView listName = (TextView) findViewById(R.id.list_title);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString(getApplicationContext().getString(R.string.tag_list_title));
        listName.setText(title);
    }
}