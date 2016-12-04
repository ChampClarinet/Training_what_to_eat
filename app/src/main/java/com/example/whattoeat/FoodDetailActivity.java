package com.example.whattoeat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattoeat.Model.Food;
import com.example.whattoeat.Utilities.Utils;

public class FoodDetailActivity extends AppCompatActivity {

    private static final String TAG = FoodDetailActivity.class.getSimpleName();

    private Food targetFood;

    private TextView foodTextView;
    private ImageView foodImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        foodImageView = (ImageView) findViewById(R.id.food_detail_image_view);
        foodTextView = (TextView) findViewById(R.id.food_detail_text_view);

        targetFood = (Food) getIntent().getSerializableExtra("food");

        foodImageView.setImageDrawable(Utils.getDrawableFromAssets(this, targetFood.pictureFileName));
        foodTextView.setText(targetFood.name);

    }
}
