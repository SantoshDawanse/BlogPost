package com.santosh.dawn.blogpost;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by dawn on 8/10/2016.
 */
public class BlogpostDB {

    //database name
    public static final int DB_VERSION = 2;
    public static final String DB_NAME = "blogpost.db";

    //table name
    public static final String TABLE_BLOGPOST = "blogpost_table";

    //table fields
    public static final String KEY_ID = "_id";
    public static final String KEY_DATE = "date";
    public static final String KEY_TIME = "time";
    public static final String KEY_POST = "post";

    //table creation
    private static final String CREATE_TABLE_BLOGPOST = "CREATE TABLE " + TABLE_BLOGPOST + " ("
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + KEY_DATE + " TEXT, "
            + KEY_TIME + " TEXT, "
            + KEY_POST + " TEXT"
            + ")";

    private BlogpostDBHelper mDbHelper;
    private SQLiteDatabase mDatabase;

    //constructor creation
    public BlogpostDB(Context context) {
        mDbHelper = new BlogpostDBHelper(context);
        mDatabase = mDbHelper.getWritableDatabase();
    }

    //open a database
    public void open() throws SQLException {
        mDatabase = mDbHelper.getWritableDatabase();
    }

    //close a database
    public void close(){
        mDatabase.close();
    }

    //inserting data into a table
    public void insertData(String post){
        ContentValues cv = new ContentValues();
        cv.put(KEY_DATE, getDate());
        cv.put(KEY_TIME, getTime());
        cv.put(KEY_POST, post);
        mDatabase.insert(TABLE_BLOGPOST, null, cv);
    }

    //getting current date
    private String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = new Date();
        return dateFormat.format(date);
    }

    //getting current time
    private String getTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        Date date = new Date();
        return timeFormat.format(date);
    }

    //retrieving data from table
    public Cursor getAllData() {
        String query = "SELECT * FROM " + TABLE_BLOGPOST + " ORDER BY " + KEY_ID + " DESC";
        return mDatabase.rawQuery(query, null);
    }

    //helper class
    public class BlogpostDBHelper extends SQLiteOpenHelper {
        public BlogpostDBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_TABLE_BLOGPOST);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_BLOGPOST);
            onCreate(db);

        }
    }
}
