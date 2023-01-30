package com.example.testdatabase.MyDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testdatabase.Mobile;

import java.util.ArrayList;

public class MyDataBaseThree extends SQLiteOpenHelper {

    public static final String NAME_DATA_BASE = "myDataBase" ;
    public static final int VERSION = 1 ;

    public static final String NAME_TABLE = "mobile";
    //items
    public static final String ID_item = "id";
    public static final String COLOR_MOBILE = "color";
    public static final String COMPANY_MOBILE = "company";
    public static final String PRICE_MOBILE = "price";



    public MyDataBaseThree (Context context){
        super(context,NAME_DATA_BASE,null,VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " +NAME_TABLE+" ("+ID_item+" INTEGER PRIMARY KEY AUTOINCREMENT  " +
                ", "+COLOR_MOBILE+" TEXT,  "+COMPANY_MOBILE+" TEXT,  "+PRICE_MOBILE+" TEXT ) ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NAME_TABLE);
        onCreate(sqLiteDatabase);
    }


    public boolean insertMobile (Mobile mobile){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLOR_MOBILE,mobile.getColor());
        values.put(COMPANY_MOBILE,mobile.getCompany());
        values.put(PRICE_MOBILE,mobile.getPrice());

        long ln = database.insert(NAME_TABLE,null,values);
        return ln > 0 ;
    }

    public ArrayList<Mobile> getAllMobile (){
        ArrayList<Mobile>mobileArrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();

        Cursor cursor = database.rawQuery("SELECT * FROM " + NAME_TABLE ,null);

        if (cursor.moveToFirst()) {
            do {
                String color = cursor.getString(0);
                String company = cursor.getString(1);
                String price = cursor.getString(2);

                Mobile mobile = new Mobile(company , color , price );
                mobileArrayList.add(mobile);

            } while (cursor.moveToNext());
            cursor.close();

        }
        return mobileArrayList ;

    }







}
