package com.example.finaltestjava;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DBHelperBusiness extends SQLiteOpenHelper {
    public static final String DBNAME = "Login3.db";
    public DBHelperBusiness(Context context) {
        super(context, "Login3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table user(username TEXT primary key, password TEXT, serviceDetails TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists user");
    }

    public Boolean insertData(String username, String password, String serviceDetails){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("serviceDetails", serviceDetails);
        long result = MyDB.insert("user", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public void updateData(String username, String password, String serviceDetails){
        //get writable database to write data on db
        SQLiteDatabase Mydb = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put("serviceDetails", serviceDetails);

        Mydb.update("user",contentValues,"username =? ",new String[]{username} );

        Mydb.close();
    }

    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from user where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}