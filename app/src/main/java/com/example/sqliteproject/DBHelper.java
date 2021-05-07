package com.example.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context,"Userdata.db",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Userdetails(name TEXT primary key,email TEXT,password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if exists Userdetails");
    }
    public Boolean insertUserData(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("password",password);
        long result = db.insert("Userdetails",null,contentValues);
        if(result==-1) return false;
        else return true;
    }

    public Boolean updateUserData(String name, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("email",email);
        contentValues.put("password",password);
        Cursor cursor =db.rawQuery("select * from Userdetails where name =?",new String[]{name});
        if(cursor.getCount()>0){


            long result = db.update("Userdetails",contentValues,"name=?",new String[]{name});
            if(result==-1) return false;
            else return true;

        }
        else return false;
    }

    public Boolean deleteUserData(String name){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =db.rawQuery("select * from Userdetails where name =?",new String[]{name});
        if(cursor.getCount()>0){
            long result = db.delete( "Userdetails","name=?",new String[]{name});
            if(result==-1) return false;
            else return true;

        }
        else return false;
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdetails", null);
        return cursor;
    }
}
