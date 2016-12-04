package com.example.whattoeat.Utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Utils {

    public static Drawable getDrawableFromAssets(Context context, String pictureFileName) {
        AssetManager am = context.getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            return drawable;
        } catch (IOException e) {
            e.printStackTrace();

            try {
                InputStream stream = new FileInputStream(pictureFileName);
                Drawable drawable = Drawable.createFromStream(stream, null);
                return drawable;
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
            }

        }
        return null;
    }

}
