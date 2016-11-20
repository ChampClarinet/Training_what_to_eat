package com.example.whattoeat.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.whattoeat.DB.databaseHelper;

import java.util.ArrayList;
import java.util.Random;

public class FoodMenu {

    private String TAG = FoodMenu.class.getSimpleName();

    private static databaseHelper helper;
    private static SQLiteDatabase db;

    private static Random mRandom = new Random();

    /**
     * Singleton pattern
     * 2 following classes are OOP pattern to make sure this class can be create only a single time.
     */

    private static FoodMenu mInstance;
    private static Context context;

    private static ArrayList<Food> mFoodList = new ArrayList();

    public static FoodMenu getInstance(Context context){
        if(mInstance == null){
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
        helper = new databaseHelper(context);
        db = helper.getWritableDatabase();

        Cursor cursor = db.query(databaseHelper.TABLE_NAME, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_NAME));
            String picture = cursor.getString(cursor.getColumnIndex(databaseHelper.COL_PICTURE));
            mFoodList.add(new Food(name, picture));
        }

        cursor.close(); // unnecessary, but should

    }

    public ArrayList<Food> getFoodList() {
        return mFoodList;
    }

    public Food getRandomFood(){
        int randomIndex = mRandom.nextInt(mFoodList.size());
        Food food = mFoodList.get(randomIndex);
        Log.i(TAG, food.name + ", " + food.pictureFileName);
        return food;
    }

}
