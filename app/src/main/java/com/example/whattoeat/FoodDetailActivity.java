package com.example.whattoeat;

import android.content.DialogInterface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.whattoeat.Adapters.FoodPagerAdapter;
import com.example.whattoeat.Model.Food;
import com.example.whattoeat.Model.FoodMenu;

public class FoodDetailActivity extends AppCompatActivity {

    private static final String TAG = FoodDetailActivity.class.getSimpleName();

    //private Food targetFood;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        //targetFood = (Food) getIntent().getSerializableExtra("food");

        FoodPagerAdapter adapter = new FoodPagerAdapter(this, getSupportFragmentManager());
        pager = findViewById(R.id.view_pager);
        //pager.setCurrentItem(position);
        pager.setAdapter(adapter);

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

    @Override
    public void onResume() {
        super.onResume();
        final int position = getIntent().getIntExtra("position", 0);
        pager.postDelayed(new Runnable() {
            @Override
            public void run() {
                pager.setCurrentItem(position);
            }
        }, 100);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_food_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_delete_food) {
            FoodMenu menu = FoodMenu.getInstance(this);
            int position = pager.getCurrentItem();
            final Food food = menu.getFoodList().get(position);
            Log.d(TAG, "deleting " + food.name);
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            dialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    delete(food);
                }
            });
            dialog.setTitle(getString(R.string.delete_confirm_title));
            dialog.setMessage(getString(R.string.delete_confirm) + ", " + food.name);
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void delete(Food food) {
        FoodMenu menu = FoodMenu.getInstance(this);
        menu.delete(food.id);
        Toast.makeText(this, food.name + getString(R.string.deleted), Toast.LENGTH_SHORT).show();
        finish();
    }

}
