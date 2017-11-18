package com.example.whattoeat.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whattoeat.Model.Food;
import com.example.whattoeat.R;
import com.example.whattoeat.Utilities.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FoodDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FoodDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_FOOD = "name";

    // TODO: Rename and change types of parameters
    private Food mFood;


    public FoodDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param food Parameter 1.
     * @return A new instance of fragment FoodDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodDetailFragment newInstance(Food food) {
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_FOOD, food);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFood = (Food) getArguments().getSerializable(ARG_FOOD);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_food_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView foodNameTextView = view.findViewById(R.id.food_detail_text_view);
        ImageView foodNameImageView = view.findViewById(R.id.food_detail_image_view);

        foodNameTextView.setText(mFood.name);
        foodNameImageView.setImageDrawable(Utils.getDrawableFromAssets(getActivity(), mFood.pictureFileName));

    }

}
