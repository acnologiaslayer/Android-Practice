package com.mahir.databaseresponse;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by mahir on 5/20/16.
 */
public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Records";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Students";
    private static final String ID = "_ID";
    private static final String NAME = "NAME";
    private static final String CLASS = "CLASS";
    private static final String EMAIL = "EMAIL";
    private static final String CREATE_TABLE = "CREATE TABLE "+
            TABLE_NAME+"("+ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
            NAME+" TEXT NOT NULL,"+
            CLASS+" TEXT NOT NULL,"+
            EMAIL+" TEXT NOT NULL);";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("ALTER TABLE "+TABLE_NAME+" ADD COLUMN ADDRESS TEXT");
    }

    public long inset(Student_Detail sd){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(NAME,sd.getName());
        cv.put(CLASS,sd.getClass_name());
        cv.put(EMAIL,sd.getEmail());
        return db.insert(TABLE_NAME,null,cv);
    }
}
