package com.eipipuz.ivaslist;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

import java.sql.SQLException;
import java.util.List;

public class HomeActivity extends ListActivity {
    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    private TagsDataSource mTagsDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTagsDataSource = new TagsDataSource(this);

        try {
            mTagsDataSource.open();
        } catch (SQLException ex) {
            Log.e(LOG_TAG, "Cannot open database");
            //TODO Add some dialog or something here.
        }

        List<Tag> tags = mTagsDataSource.getAllTags();
        ArrayAdapter<Tag> adapter = new ArrayAdapter<Tag>(this, R.layout.list_tag, tags);

        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void addTag(View view) {
        Log.i(LOG_TAG, "create new tag");


    }
}
