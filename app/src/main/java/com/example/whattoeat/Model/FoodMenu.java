package com.example.whattoeat.Model;

import android.content.Context;

/**
 * Singleton pattern
 * design OOP pattern to make sure this class can be create only a single time.
 */

public class FoodMenu {

    private static FoodMenu mInstance;
    private Context context;

    public static FoodMenu getInstance(Context context){
        if(mInstance == null){
            mInstance = new FoodMenu(context);
        }
        return mInstance;
    }

    private FoodMenu(Context context) {
        this.context = context;
    }

}
