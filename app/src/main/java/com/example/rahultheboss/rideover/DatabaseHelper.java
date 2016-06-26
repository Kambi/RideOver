package com.example.rahultheboss.rideover;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by ttwin on 4/16/2016.
 */
class DatabaseHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Rideover.db";

    //table for sign up activity
    private static final String TABLE_CONTACTS = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_PASSWORD = "password";



    //table for share a ride activity

    private static final String TABLE_RIDES = "rides";
    private static final String COLUMN_RIDES_ID = "id";
    private static final String COLUMN_RIDES_NAME = "sr_name";
    private static final String COLUMN_RIDES_LEAVING_FROM = "sr_leaving_from";
    private static final String COLUMN_RIDES_GOING_TO = "sr_going_to";
    private static final String COLUMN_RIDES_DATE = "sr_date";
    private static final String COLUMN_RIDES_TIME = "sr_time";
    private static final String COLUMN_RIDES_SEATS = "sr_seats";
    private static final String COLUMN_RIDES_PRICE = "sr_price";
    private static final String COLUMN_RIDES_PHONE_NUMBER = "sr_phone_number";


    SQLiteDatabase db;


    private static final String TABLE_CONTACTS_2 = "create table contacts (id integer primary key not null , "
            + "name text not null, username text not null, email text not null, phone_number text not null, " +
            "password text not null)";

    private static final String TABLE_RIDES_2 = "create table rides (id integer primary key not null , "
            + "sr_name text not null, sr_leaving_from text not null, sr_going_to text not null, sr_date text not null, " +
            "sr_time text not null, sr_seats text not null, sr_price text not null, " +
            "sr_phone_number text not null)";


    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        db.execSQL(TABLE_CONTACTS_2);
        db.execSQL(TABLE_RIDES_2);
        this.db = db;
    }
    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from contacts";
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();
        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAME, c.getName());
        values.put(COLUMN_USERNAME, c.getUsernname());
        values.put(COLUMN_EMAIL, c.getEmail());
        values.put(COLUMN_PHONE_NUMBER, c.getPhoneNumber());
        values.put(COLUMN_PASSWORD, c.getPassword());


        db.insert(TABLE_CONTACTS, null, values);
        db.close();


    }

    public boolean checkEmail(String email2){
        db = this.getReadableDatabase();
        String query2 = "select email from "+ TABLE_CONTACTS + " where email = " + "'" + email2 + "'";
        Cursor check = db.rawQuery(query2,null);
        if(check.getCount() > 0){
            return true;
        }
        else {
            return false;
        }

    }

    public boolean checkUsername(String username2){
        db = this.getReadableDatabase();
        String query2 = "select username from "+ TABLE_CONTACTS + " where username = " + "'" + username2 + "'";
        Cursor check = db.rawQuery(query2,null);
        if(check.getCount() > 0){
            return true;
        }
        else {
            return false;
        }

    }

    public boolean checkFirstName(String firstName){
        db = this.getReadableDatabase();
        String query2 = "select name from "+ TABLE_CONTACTS + " where name = " + "'" + firstName + "'";
        Cursor check = db.rawQuery(query2,null);
        if(check.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }



    public void insertRides(Rides r){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from rides";
        Cursor cursor = db.rawQuery(query, null);

        int count = cursor.getCount();

        values.put(COLUMN_RIDES_ID, count);
        values.put(COLUMN_RIDES_NAME, r.getSr_name());
        values.put(COLUMN_RIDES_LEAVING_FROM, r.getSr_leaving_from());
        values.put(COLUMN_RIDES_GOING_TO, r.getSr_going_to());
        values.put(COLUMN_RIDES_DATE, r.getSr_date());
        values.put(COLUMN_RIDES_TIME, r.getSr_time());
        values.put(COLUMN_RIDES_SEATS, r.getSr_seats());
        values.put(COLUMN_RIDES_PRICE, r.getSr_price());
        values.put(COLUMN_RIDES_PHONE_NUMBER, r.getSr_phoneNumber());

        db.insert(TABLE_RIDES, null, values);
        db.close();
    }


    public String search_password_string(String username){
        db = this.getReadableDatabase();
        String query = "select username, password from " + TABLE_CONTACTS;
        Cursor cursor = db.rawQuery(query, null);
        String a,b;
        b = "not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);


                if(a.equals(username)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());

        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS" + TABLE_CONTACTS;
        query = "DROP TABLE IF EXISTS" + TABLE_RIDES;
        db.execSQL(query);

        this.onCreate(db);
    }

    public Cursor getAllTheData(){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from rides", null);
        return res;

    }

    public Cursor getAllData(String lf, String gt, String date){
        db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_RIDES + " where sr_leaving_from = " + "'" +lf + "'"
                + " and sr_going_to = " + "'" + gt + "'" + " and sr_date = " + "'" + date + "'", null);
        return res;

    }


}
