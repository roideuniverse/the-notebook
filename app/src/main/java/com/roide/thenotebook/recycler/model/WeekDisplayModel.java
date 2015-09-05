package com.roide.thenotebook.recycler.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.carrotcreative.recyclercore.adapter.RecyclerCoreModel;
import com.roide.thenotebook.R;
import com.roide.thenotebook.recycler.controller.WeekDisplayController;

/**
 * Created by roide on 9/5/15.
 */
public class WeekDisplayModel extends RecyclerCoreModel
{
    private String mStrDate;

    @Override
    public RecyclerCoreController buildController(LayoutInflater inflater, ViewGroup parent)
    {
        View rootView = inflater.inflate(R.layout.element_week_display, parent, false);
        return new WeekDisplayController(rootView);
    }

    public WeekDisplayModel setDate(String date)
    {
        mStrDate = date;
        return this;
    }

    public String getStrDate()
    {
        return mStrDate;
    }
}
