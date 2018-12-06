package com.example.reddwarf.smsforwarder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SettingsDatabase {
    static final String DATABASE_NAME = "SMSForwarddatabase.db";
    String ok="OK";
    static final int DATABASE_VERSION = 1;
    public  static String getValue="";
    public static final int NAME_COLUMN = 1;

    static final String DATABASE_CREATE = "create table SETTINGS( ID integer primary key autoincrement,NAME  text,VALUE  text); ";

    public static SQLiteDatabase db;

    private final Context context;

    private static SMSFDatabase dbHelper;
    public  SettingsDatabase(Context _context)
    {
        context = _context;
        dbHelper = new SMSFDatabase(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public  SettingsDatabase open() throws SQLException
    {
        db = dbHelper.getWritableDatabase();        return this;
    }

    public void close()
    {
        db.close();
    }

    public  SQLiteDatabase getDatabaseInstance()
    {
        return db;
    }

    public String insertEntry(String name,String value)
    {
        try {
            ContentValues newValues = new ContentValues();

            newValues.put("NAME", name);
            newValues.put("VALUE", value);


            db = dbHelper.getWritableDatabase();
            long result=db.insert("SETTINGS", null, newValues);
            System.out.print(result);
            //Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
        }catch(Exception ex) {
            System.out.println("Exceptions " +ex);
            Log.e("Note", "One row entered");
        }
        return ok;
    }

    public String getSinlgeEntry(String name)
    {
        db=dbHelper.getReadableDatabase();
        Cursor cursor=db.query("SETTINGS", null, "NAME=?", new String[]{name}, null, null, null);
        if(cursor.getCount()<1)
            return "NOT EXIST";
        cursor.moveToFirst();
        getValue= cursor.getString(cursor.getColumnIndex("VALUE"));
        return getValue;
    }

    public void  updateEntry(String name,String value)
    {

        ContentValues updatedValues = new ContentValues();

        updatedValues.put("VALUE", value);
        String where="NAME = ?";
        db.update("SETTINGS",updatedValues, where, new String[]{name});
    }

    public void  deleteEntry(String name)
    {
        String where="NAME = ?";
        db.delete("SETTINGS", where, new String[]{name});
    }

}
