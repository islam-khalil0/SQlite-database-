package com.example.testdatabase.MyDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.testdatabase.Shoe;

import java.util.ArrayList;

public class MyDataBaseTwo extends SQLiteOpenHelper {

    public static final String NAME_DATABASE = "SHOES";
    public static final int VERSION = 1 ;

    public static final String NAME_TABLE = "shoe";
    //items
    public static final String SIZE = "M";
    public static final String COLOR = "NORMAL";
    public static final String TYPE = "NIKE";
    public static final String ID = "id" ;



        public  MyDataBaseTwo (Context context ){
            super(context,NAME_DATABASE,null,VERSION);
        }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("CREATE TABLE "+NAME_TABLE+" ( "+ID+" INTEGER PRIMARY KEY AUTOINCREMENT" +
                    ",  "+SIZE+" TEXT,  "+COLOR+" TEXT,   "+TYPE+" TEXT ) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NAME_TABLE);
            onCreate(sqLiteDatabase);
    }

     public boolean insertShoes (Shoe shoe){

         SQLiteDatabase database = getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(SIZE , shoe.getSize());
         values.put(COLOR ,shoe.getColor());
         values.put(TYPE , shoe.getType());

         long ln = database.insert(NAME_TABLE , null , values);

         return ln > 0 ;
     }



     public boolean upDataShoe (Shoe shoe){

            SQLiteDatabase database = getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(SIZE , shoe.getSize());
            values.put(COLOR ,shoe.getColor());
            values.put(TYPE , shoe.getType());

            long ln = database.update(NAME_TABLE,values,"WHERE"+SIZE+"=?" ,new String [] {shoe.getSize()});
            return ln > 0 ;
     }


     public long getShoesCount (){
            SQLiteDatabase database = getReadableDatabase();
            return DatabaseUtils.queryNumEntries(database,NAME_TABLE);
     }


     public ArrayList<Shoe> getAllShoes (){
      ArrayList<Shoe>shoeArrayList = new ArrayList<>();
      SQLiteDatabase database = getReadableDatabase();

         Cursor cursor = database.rawQuery("SELECT * FROM "+ NAME_TABLE ,null );

         if (cursor.moveToFirst()){
             do {
                 String size = cursor.getString(0);
                 String color = cursor.getString(1);
                 String type = cursor.getString(2);

                 Shoe shoe = new Shoe(color,size,type);
                 shoeArrayList.add(shoe);

             }while (cursor.moveToNext());
             cursor.close();

         }
         return shoeArrayList ;
     }



     public ArrayList<Shoe> getShoes (String sizeSearch){
      ArrayList<Shoe>shoeArrayList = new ArrayList<>();
      SQLiteDatabase database = getReadableDatabase();

         Cursor cursor = database.rawQuery("SELECT * FROM "+ NAME_TABLE +" WHERE "+ SIZE +"=?", new String[]{sizeSearch} );

         if (cursor.moveToFirst()){
             do {
                 String size = cursor.getString(0);
                 String color = cursor.getString(1);
                 String type = cursor.getString(2);

                 Shoe shoe = new Shoe(color,size,type);
                 shoeArrayList.add(shoe);

             }while (cursor.moveToNext());
             cursor.close();

         }
         return shoeArrayList ;
     }




}
