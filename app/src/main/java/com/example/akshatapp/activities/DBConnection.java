package com.example.akshatapp.activities;

import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class DBConnection extends AppCompatActivity {
    SQLiteDatabase db= null;
    public SQLiteDatabase getdb()
    {
        db = this.openOrCreateDatabase("instagram",MODE_PRIVATE,null);
        return db;
    }


}
