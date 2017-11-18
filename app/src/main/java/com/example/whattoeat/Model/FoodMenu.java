package com.example.whattoeat.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.whattoeat.DB.DatabaseHelper;

import java.util.ArrayList;
import java.util.Random;

public class FoodMenu {

    private String TAG = FoodMenu.class.getSimpleName();

    private static DatabaseHelper helper;
    private static SQLiteDatabase db;

    private static Random mRandom = new Random();

    /**
     * Singleton pattern
     * 2 following classes are OOP pattern to make sure this class can be create only a single time.
     */

    private static FoodMenu mInstance;
    private static Context context;

    private static ArrayList<Food> mFoodList = new ArrayList();

    public static FoodMenu getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new FoodMenu(context);
        }
        return mInstance;
    }

    private FoodMenu(Context context) {
        this.context = context;
        loadFromDatabase();
    }

    public static void loadFromDatabase() {

        mFoodList.clear();
        helper = new DatabaseHelper(context);
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(DatabaseHelper.TABLE_NAME, null, null, null, null, null, null);

        String s = "";
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COL_ID));
            String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_NAME));
            String picture = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COL_PICTURE));
            Food food = new Food(id, name, picture);
            s += food + "\n";
            mFoodList.add(food);
        }

        Log.d("Load", s);

        cursor.close(); // unnecessary, but should

    }

    public void delete(int id) {
        helper = new DatabaseHelper(context);
        db = helper.getReadableDatabase();
        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.COL_ID + " = " + id, null);
        loadFromDatabase();
    }

    public ArrayList<Food> getFoodList() {
        return mFoodList;
    }

    public Food getRandomFood() {
        int randomIndex = mRandom.nextInt(mFoodList.size());
        Food food = mFoodList.get(randomIndex);
        Log.i(TAG, food.name + ", " + food.pictureFileName);
        return food;
    }

}
