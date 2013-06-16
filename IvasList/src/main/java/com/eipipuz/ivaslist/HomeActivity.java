package com.eipipuz.ivaslist;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import java.sql.SQLException;
import java.util.List;

import static android.app.AlertDialog.*;

public class HomeActivity extends ListActivity {
    private static final String LOG_TAG = HomeActivity.class.getSimpleName();
    private TagsDataSource mTagsDataSource;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTagsDataSource = new TagsDataSource(this);

        try {
            mTagsDataSource.open();
        } catch (SQLException ex) {
            Log.e(LOG_TAG, "Cannot open database");
            //TODO Add some dialog or something here.
        }

        final List<Tag> tags = mTagsDataSource.getAllTags();
        final ArrayAdapter<Tag> adapter = new ArrayAdapter<Tag>(this, R.layout.list_tag, tags);

        setListAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public void addTag(final View view) {
        Log.i(LOG_TAG, "create new tag");

        final LayoutInflater li = getLayoutInflater();
        final View addTagView = li.inflate(R.layout.add_tag, null);
        final Builder alertDialogBuilder = new Builder(view.getContext());
        alertDialogBuilder.setView(addTagView);
        alertDialogBuilder.setTitle(R.string.add_tag_title);
        alertDialogBuilder.setPositiveButton(R.string.add_tag_ok_button, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                EditText editText = (EditText)addTagView.findViewById(R.id.tag_name_input);
                final String tagName = editText.getText().toString();
                final Tag tag = mTagsDataSource.createTag(tagName);

                final ArrayAdapter<Tag> adapter = (ArrayAdapter<Tag>)getListAdapter();
                adapter.add(tag);
                adapter.notifyDataSetChanged();
            }
        });
        alertDialogBuilder.setNegativeButton(R.string.add_tag_cancel_button, new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int id) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();


    }
}
