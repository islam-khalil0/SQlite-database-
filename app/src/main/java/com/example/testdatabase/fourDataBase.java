package com.example.testdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testdatabase.MyDataBase.MyDataBase;

import java.util.ArrayList;

public class fourDataBase extends SQLiteOpenHelper {

    public static final String DB_NAME = "car" ;
    public static final int DB_VERSION = 1 ;

    public static final String TB_CAR = "car" ;

    //items
    public static final String CAR_CLN_COLOR = "black" ;
    public static final String CAR_CLN_MODEL = "KIA" ;
    public static final String  CAR_CLN_tigerPlate = "plate" ;



    public fourDataBase (Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+TB_CAR+" ( "+CAR_CLN_COLOR+" TEXT , "+CAR_CLN_MODEL+" TEXT , "+CAR_CLN_tigerPlate+" TEXT ) " );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TB_CAR);
        onCreate(sqLiteDatabase);

    }



    public boolean insertCar (Car car ){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(CAR_CLN_COLOR,car.getColor());
        values.put(CAR_CLN_MODEL,car.getModel());
        values.put(CAR_CLN_tigerPlate,car.getTigerPlate());

        long result = database.insert(TB_CAR,null,values);

        return result > 0 ;

    }


    public boolean upDataCar (Car car){

        SQLiteDatabase database = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(CAR_CLN_MODEL,car.getModel());
        values.put(CAR_CLN_COLOR,car.getColor());
        values.put(CAR_CLN_tigerPlate,car.getTigerPlate());

        String args [] = {car.getModel()};

        long result = database.update(TB_CAR,values,"model=?" , args );
        return result > 0 ;

    }

    public long getCountCar (){
        SQLiteDatabase database = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(database,TB_CAR);
    }


    public ArrayList<Car> getAllCars (){

        ArrayList<Car> carArrayList = new ArrayList<>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TB_CAR , null);

        if (cursor.moveToFirst()){

            do {
                String model = cursor.getString(0);
                String color = cursor.getString(1);
                String tigerPlate = cursor.getString(2);

                Car car = new Car(model,color,tigerPlate);

                carArrayList.add(car);

            }while (cursor.moveToNext());

            cursor.close();

        }
        return carArrayList ;
    }


    public ArrayList<Car> getCar (String modelSearch){

        ArrayList<Car>carArrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TB_CAR + " WHERE " + CAR_CLN_MODEL +"=?" ,new String [] {modelSearch} );

        if (cursor.moveToFirst()){

            do {
                String model = cursor.getString(0);
                String color = cursor.getString(1);
                String tigerPlate = cursor.getString(2);

                Car car = new Car(model,color,tigerPlate);

                carArrayList.add(car);

            }while (cursor.moveToNext());

            cursor.close();
        }

        return carArrayList;

    }



}
