package com.example.cristhianpinzon.picoyplaca.Persistence;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by cristhianpinzon on 18/07/17.
 */

public class DBOpenHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "bdpyc.db";
    private static final int DATABASE_VERSION = 1;


    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
