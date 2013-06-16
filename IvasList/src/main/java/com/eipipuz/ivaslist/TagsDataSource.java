package com.eipipuz.ivaslist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class TagsDataSource {
    private static final String LOG_TAG = TagsDataSource.class.getSimpleName();
    private SQLiteDatabase mDatabase;
    private AppSQLiteHelper mAppSQLiteHelper;

    public TagsDataSource(Context context) {
        mAppSQLiteHelper = new AppSQLiteHelper(context);
    }

    public void open() throws SQLException {
        mDatabase = mAppSQLiteHelper.getWritableDatabase();
    }

    public void close() {
        mAppSQLiteHelper.close();
    }

    public Tag createTag(String tagName) {
        ContentValues values = new ContentValues();
        values.put(AppSQLiteHelper.TAGS_NAME, tagName);

        int newId = (int)mDatabase.insert(AppSQLiteHelper.TAGS_TABLE, null, values);

        if (newId == -1) {
            Log.e(LOG_TAG, "Cannot creat new tag: " + tagName);
            return null;
        }

        Tag newTag = new Tag(newId, tagName);
        return newTag;
    }

    public void deleteTag(Tag tag) {
        Log.i(LOG_TAG, "Deleting tag: " + tag.getName());

        mDatabase.delete(AppSQLiteHelper.TAGS_TABLE,
                AppSQLiteHelper.TAGS_ID + " = " + tag.getId(),
                null);
    }

    public List<Tag> getAllTags() {
        List<Tag> allTags = new ArrayList<Tag>();

        Cursor cursor = mDatabase.query(AppSQLiteHelper.TAGS_TABLE,
                AppSQLiteHelper.TAGS_COLUMNS, null, null, null, null, null); // TODO Add limit later

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            allTags.add(cursorToTag(cursor));
            cursor.moveToNext();
        }
        cursor.close();

        return allTags;
    }

    private Tag cursorToTag(Cursor cursor) {
        return new Tag(cursor.getInt(0), cursor.getString(1));
    }
}