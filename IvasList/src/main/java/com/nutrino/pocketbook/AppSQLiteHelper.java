package com.nutrino.pocketbook;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AppSQLiteHelper extends SQLiteOpenHelper {
    private static final String LOG_TAG = AppSQLiteHelper.class.getSimpleName();

    private static final String DATABASE_NAME = "pocketbook.db";
    private static final int DATABASE_VERSION = 1;

    private static final String ID_NAME = "_id";

    public static final String TAGS_TABLE = "tags";
    public static final String TAGS_ID = ID_NAME;
    public static final String TAGS_NAME = "tag_name";
    public static final String[] TAGS_COLUMNS = {TAGS_ID, TAGS_NAME};

    private static final String TAG_CREATE = "create table " + TAGS_TABLE + "(" + TAGS_ID +
            " integer primary key autoincrement, " + TAGS_NAME + " text not null);";
    private static final String TAG_DELETE = "drop table if exists " + TAGS_TABLE;

    public AppSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TAG_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int originalVersion, int newVersion) {
        Log.i(LOG_TAG, "Upgrading database from " + originalVersion + " to " + newVersion);

        sqLiteDatabase.execSQL(TAG_DELETE);
        onCreate(sqLiteDatabase);
    }
}
