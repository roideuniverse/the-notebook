package com.roide.thenotebook.recycler.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.carrotcreative.recyclercore.adapter.RecyclerCoreModel;
import com.roide.thenotebook.R;
import com.roide.thenotebook.backend.DayEntry;
import com.roide.thenotebook.recycler.controller.DayEntryController;

/**
 * Created by roide on 8/9/15.
 */
public class DayEntryModel extends RecyclerCoreModel {

    private DayEntry mDayEntry;

    @Override
    public RecyclerCoreController buildController(LayoutInflater inflater, ViewGroup parent) {
        View root = inflater.inflate(R.layout.element_day_entry, parent, false);
        return new DayEntryController(root);
    }

    public DayEntryModel addEntry(DayEntry dayEntry) {
        mDayEntry = dayEntry;
        return this;
    }

    public DayEntry getEntryList() {
        return mDayEntry;
    }
}
