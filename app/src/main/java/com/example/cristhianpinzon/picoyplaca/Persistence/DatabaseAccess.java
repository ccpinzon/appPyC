package com.example.cristhianpinzon.picoyplaca.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cristhianpinzon.picoyplaca.Logic.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cristhianpinzon on 18/07/17.
 */

public class DatabaseAccess {

    public static final String TAG = "PRUEBA_DATABASEACCESS";
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;

    private static DatabaseAccess instance;


    public DatabaseAccess(Context context) {
        this.openHelper =  new DBOpenHelper(context);
    }

    public static DatabaseAccess getInstance(Context context){
        if (instance == null)
            instance = new DatabaseAccess(context);

        return instance;
    }
    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<User> getUsers(){
        List<User> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM USER",null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()){
            int numplaca = cursor.getInt(0);
            String tipoCarro = cursor.getString(1);
            list.add(new User(numplaca,tipoCarro));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public void deleteUser (){

            database.execSQL("DELETE FROM USER");

    }

    public void addUser (User user) {
        if (user!=null){
            ContentValues values = new ContentValues();
            values.put("num_placa",user.getNum_placa());
            values.put("tipo_carro",user.getTipo_carro());
            database.insert("USER",null,values);
            database.close();
        }else {
            Log.e(TAG,"Error adduser en el metodo");
        }
    }

}
