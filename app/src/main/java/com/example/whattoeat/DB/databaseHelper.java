package com.example.whattoeat.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class databaseHelper extends SQLiteOpenHelper{

    private static final String TAG = databaseHelper.class.getSimpleName();

    private static final String DATABASENAME = "WHAT_TO_EAT";
    private static final int DATABASEVERSION = 1;
    public static final String TABLE_NAME = "FOOD";
    public static final String COL_ID = "_ID";
    public static final String COL_NAME = "name";
    public static final String COL_PICTURE = "picture";

    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COL_NAME + " TEXT," +
            COL_PICTURE + " TEXT" +
            ")";

    public databaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(SQL_CREATE_TABLE);
        insertInitialData(db);
    }

    private void insertInitialData(SQLiteDatabase db) {

        ContentValues cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวไข่เจียว");
        cv.put(COL_PICTURE, "kao_kai_jeaw.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวหมูแดง");
        cv.put(COL_PICTURE, "kao_moo_dang.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวมันไก่");
        cv.put(COL_PICTURE, "kao_mun_kai.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวหน้าเป็ด");
        cv.put(COL_PICTURE, "kao_na_ped.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ข้าวผัด");
        cv.put(COL_PICTURE, "kao_pad.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ผัดซีอิ๊ว");
        cv.put(COL_PICTURE, "pad_sie_eew.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ผัดไทย");
        cv.put(COL_PICTURE, "pad_thai.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ราดหน้า");
        cv.put(COL_PICTURE, "rad_na.jpg");
        db.insert(TABLE_NAME, null, cv);

        cv = new ContentValues();
        cv.put(COL_NAME, "ส้มตำ ไก่ย่าง");
        cv.put(COL_PICTURE, "som_tum_kai_yang.jpg");
        db.insert(TABLE_NAME, null, cv);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
