package com.example.whattoeat.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    private static final String ARG_FOOD_NAME = "name";
    private static final String ARG_FOOD_PICTURE = "picture";

    // TODO: Rename and change types of parameters
    private String mFoodName;
    private String mFoodPicture;


    public FoodDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param name Parameter 1.
     * @param picture Parameter 2.
     * @return A new instance of fragment FoodDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FoodDetailFragment newInstance(String name, String picture) {
        FoodDetailFragment fragment = new FoodDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_FOOD_NAME, name);
        args.putString(ARG_FOOD_PICTURE, picture);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFoodName = getArguments().getString(ARG_FOOD_NAME);
            mFoodPicture = getArguments().getString(ARG_FOOD_PICTURE);
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

        TextView foodNameTextView = (TextView) view.findViewById(R.id.food_detail_text_view);
        ImageView foodNameImageView = (ImageView) view.findViewById(R.id.food_detail_image_view);

        foodNameTextView.setText(mFoodName);
        foodNameImageView.setImageDrawable(Utils.getDrawableFromAssets(getActivity(), mFoodPicture));

    }

}
