package com.example.desinglogin;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class helper extends SQLiteOpenHelper {
    public helper(@Nullable Context context) {
        super(context, "details.db", null, 1);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table details(userid integer Primary key,postid integer,name text,email text,body text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
