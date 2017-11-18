package com.example.whattoeat.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattoeat.Model.Food;
import com.example.whattoeat.R;
import com.example.whattoeat.Utilities.Utils;

import java.util.ArrayList;

public class FoodListAdapter extends ArrayAdapter<Food>{

    private static final String TAG = FoodListAdapter.class.getSimpleName();

    private Context context;
    private int layoutResourcesID;
    private ArrayList<Food> foodList;

    public FoodListAdapter(Context context, int resource, ArrayList<Food> foodList) {
        super(context, resource, foodList);

        this.context = context;
        this.layoutResourcesID = resource;
        this.foodList = foodList;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View itemLayout = View.inflate(context, R.layout.list_item, null);
        ImageView foodImageView = itemLayout.findViewById(R.id.food_image_view);
        TextView foodTextView = itemLayout.findViewById(R.id.food_name_text_view);

        Food food = foodList.get(position);
        foodTextView.setText(food.name);
        foodImageView.setImageDrawable(Utils.getDrawableFromAssets(context, food.pictureFileName));

        return itemLayout;

    }

}
