package com.example.zc_ch.login;

/**
 * Created by ZOU Zijie on 2017/4/9.
 */


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class FoodIconGrid extends Fragment {

    private final FoodIconList foodIconList;

    public FoodIconGrid() {
        foodIconList = new FoodIconList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_restriction_grid,
                container, false);

        GridView gridview = (GridView) v.findViewById(R.id.gridViewRestrictions);

        Context ctx = container.getContext();

        if (ctx != null) {
            gridview.setAdapter(new FoodImageAdapter(ctx, foodIconList.getFoodRestrictionList(true)));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    FoodIconItem foodIconItem = foodIconList.getFoodRestrictionList(false).get(position);

                    switch (foodIconItem.getRestrictionType()) {
                        case FoodIconList.FOOD_RESTRICTION_TYPE_ALLERGIC:
                            Snackbar.make(v, getResources().getString(R.string.allergic_to) + " " + getResources().getString(foodIconItem.getNameId()), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            break;
                        case FoodIconList.FOOD_RESTRICTION_TYPE_DONT_EAT:
                            Snackbar.make(v, getResources().getString(R.string.dont_eat) + " " + getResources().getString(foodIconItem.getNameId()), Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                            break;
                    }
                }
            });
        }

        return v;
    }
}
