package com.tshibakela.mywork;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.R.attr.id;
import static android.os.Build.ID;

/**
 * Created by Tshibakela on 2017/07/18.
 */

public class myDbAdapter {


    myDbHelper myhelper;
    public myDbAdapter(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    public long insertData(String name,String surname, String contact ,String email  , String pass)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);//for name
        contentValues.put(myDbHelper.SURNAME, surname);//for surnamew
        contentValues.put(myDbHelper.CONTACT, contact);//for contact
        contentValues.put(myDbHelper.EMAIL, email);// for emails

        contentValues.put(myDbHelper.MyPASSWORD, pass);//for password
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.myID,myDbHelper.NAME,myDbHelper.SURNAME, myDbHelper.CONTACT, myDbHelper.EMAIL,  myDbHelper.MyPASSWORD};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())
        {
            int myid =cursor.getInt(cursor.getColumnIndex(myDbHelper.myID));//for an id

            String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));//for the username
            String surname =cursor.getString(cursor.getColumnIndex(myDbHelper.SURNAME));//for the surname
            String contact =cursor.getString(cursor.getColumnIndex(myDbHelper.CONTACT));//for contacts
            String email =cursor.getString(cursor.getColumnIndex(myDbHelper.EMAIL)); //for email

            String  password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            buffer.append(myid+ "   " + name + "" +surname+
                    "  "+contact+"   " +email+
                    "" + password +" \n");
        }
        return buffer.toString();
    }

    //for deleting the name on the database we use
    public  int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }


    //for updating the name on the database
    public int updateName(String oldName , String newName)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }



    //For Creating the Database

    static class myDbHelper extends SQLiteOpenHelper
    {
            private static final String DATABASE_NAME = "myDatabase";    // Database Name
            private static final String TABLE_NAME = "myTable";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String myID = "_id";     // Column 1 as Primary Key
            private static final String NAME = "Name";    //Column 2

            private static final String SURNAME="Surname";  // Column 3
            private static final String CONTACT="Contact";  // Column 4
            private static final String EMAIL="Email";      // Column 5
            private static final String MyPASSWORD = "Password";   // Column 6




        //creating the table
            private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                    " (" + myID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NAME + " VARCHAR(255) ," /**for name*/
                +SURNAME+" VARCHAR(255),"+
                CONTACT +" VARCHAR(255),"+
                EMAIL+" VARCHAR(255),"

                + MyPASSWORD + " VARCHAR(225));";

           //for deleting the table
            private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

        private Context context;

            public myDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context = context;
     }

    public void onCreate(SQLiteDatabase db)
    {

        try {
            db.execSQL(CREATE_TABLE);
        } catch (Exception e) {
            Message.message(context, "" + e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        try {
            Message.message(context, "OnUpgrade");
            db.execSQL(DROP_TABLE);
            onCreate(db);
        } catch (Exception e) {
            Message.message(context, "" + e);
        }
    }
}}
