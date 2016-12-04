package com.example.whattoeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.example.whattoeat.Adapters.FoodListAdapter;
import com.example.whattoeat.Model.FoodMenu;

public class FoodListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView foodListView = (ListView) findViewById(R.id.food_list_view);

        FoodListAdapter adapter = new FoodListAdapter(this, R.layout.list_item, FoodMenu.getInstance(this).getFoodList());
        foodListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodListActivity.this, AddFoodActivity.class));
            }
        });
    }

}
