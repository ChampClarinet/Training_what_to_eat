package com.example.whattoeat;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattoeat.Model.Food;
import com.example.whattoeat.Model.FoodMenu;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Random mRandom = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button randomButton = (Button) findViewById(R.id.random_button);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FoodMenu foodMenu = FoodMenu.getInstance(MainActivity.this);
                foodMenu.loadFromDatabase();

                Food randomFood = foodMenu.getRandomFood();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.dialog_layout, null);
                ImageView foodImage = (ImageView) layout.findViewById(R.id.food_image_view);
                TextView foodName = (TextView) layout.findViewById(R.id.food_name_text_view);

                foodName.setText(randomFood.name);
                Drawable drawable = getDrawableFromAssets(randomFood.pictureFileName);
                foodImage.setImageDrawable(drawable);

                alert.setView(layout);
                alert.show();
            }
        });
    }

    private Drawable getDrawableFromAssets(String pictureFileName) {
        AssetManager am = getAssets();
        try {
            InputStream stream = am.open(pictureFileName);
            Drawable drawable = Drawable.createFromStream(stream, null);
            return drawable;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
