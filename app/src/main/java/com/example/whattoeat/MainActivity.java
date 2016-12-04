package com.example.whattoeat;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattoeat.Model.Food;
import com.example.whattoeat.Model.FoodMenu;
import com.example.whattoeat.Utilities.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private FoodMenu foodMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        foodMenu = foodMenu.getInstance(this);
        foodMenu.loadFromDatabase();

        final Button randomButton = (Button) findViewById(R.id.random_button);
        randomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Food randomFood = foodMenu.getRandomFood();

                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View layout = inflater.inflate(R.layout.dialog_layout, null);
                ImageView foodImage = (ImageView) layout.findViewById(R.id.food_image_view);
                TextView foodName = (TextView) layout.findViewById(R.id.food_name_text_view);

                foodName.setText(randomFood.name);
                Drawable drawable = Utils.getDrawableFromAssets(MainActivity.this, randomFood.pictureFileName);
                foodImage.setImageDrawable(drawable);

                alert.setView(layout);
                alert.show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemID = item.getItemId();

        if(itemID == R.id.action_show_list){
            startActivity(new Intent(this, FoodListActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
