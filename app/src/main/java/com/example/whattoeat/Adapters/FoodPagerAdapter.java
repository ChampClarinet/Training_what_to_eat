package com.example.whattoeat.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.whattoeat.Fragments.FoodDetailFragment;
import com.example.whattoeat.Model.Food;
import com.example.whattoeat.Model.FoodMenu;

public class FoodPagerAdapter extends FragmentPagerAdapter{

    private static final String TAG = FoodListAdapter.class.getSimpleName();
    private Context context;

    public FoodPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) { // new Fragment and return to ViewPager
        FoodMenu menu = FoodMenu.getInstance(context);
        Food item = menu.getFoodList().get(position);
        FoodDetailFragment fragment = FoodDetailFragment.newInstance(item.name, item.pictureFileName);
        return fragment;
    }

    @Override
    public int getCount() {
        FoodMenu menu = FoodMenu.getInstance(context);
        return menu.getFoodList().size();
    }

}
