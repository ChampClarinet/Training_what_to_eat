package com.example.whattoeat;

import android.support.v4.app.FragmentManager; // make sure your FragmentManager is from android.support.v4.app
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattoeat.Fragments.FoodDetailFragment;
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

        targetFood = (Food) getIntent().getSerializableExtra("food");

        /*FoodDetailFragment foodDetailFragment = FoodDetailFragment.newInstance(targetFood.name, targetFood.pictureFileName); // convert fragment xml into object
        FragmentManager fm = getSupportFragmentManager();

        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.fragment_container, foodDetailFragment); // encap fragment into framelayout
        transaction.commit();
        */

        /*
        foodImageView = (ImageView) findViewById(R.id.food_detail_image_view);
        foodTextView = (TextView) findViewById(R.id.food_detail_text_view);
        foodImageView.setImageDrawable(Utils.getDrawableFromAssets(this, targetFood.pictureFileName));
        foodTextView.setText(targetFood.name);
        */

    }
}
