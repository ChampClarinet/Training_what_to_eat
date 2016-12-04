package com.example.whattoeat;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.whattoeat.Adapters.FoodListAdapter;
import com.example.whattoeat.Model.Food;
import com.example.whattoeat.Model.FoodMenu;

public class FoodListActivity extends AppCompatActivity {

    private static final String TAG = FoodListActivity.class.getSimpleName();

    private ListView foodListView;
    private FoodMenu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        foodListView = (ListView) findViewById(R.id.food_list_view);

        foodListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                FoodMenu menu = FoodMenu.getInstance(FoodListActivity.this);
                Food selected = menu.getFoodList().get(position);

                Intent intent = new Intent(FoodListActivity.this, FoodDetailActivity.class);
                intent.putExtra("food", selected);
                startActivity(intent);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FoodListActivity.this, AddFoodActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        menu = FoodMenu.getInstance(this);

        menu.loadFromDatabase();

        FoodListAdapter adapter = new FoodListAdapter(this, R.layout.list_item, menu.getFoodList());
        foodListView.setAdapter(adapter);

    }

}
