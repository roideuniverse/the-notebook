package com.roide.thenotebook.recycler.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.carrotcreative.recyclercore.adapter.RecyclerCoreModel;
import com.roide.thenotebook.R;
import com.roide.thenotebook.recycler.controller.HorizontalLineController;

/**
 * Created by roide on 9/5/15.
 */
public class HorizontalLineModel extends RecyclerCoreModel
{
    @Override
    public RecyclerCoreController buildController(LayoutInflater inflater, ViewGroup parent)
    {
        View rootView = inflater.from(parent.getContext()).inflate(R.layout.element_hr_line, parent, false);
        return new HorizontalLineController(rootView);
    }
}
