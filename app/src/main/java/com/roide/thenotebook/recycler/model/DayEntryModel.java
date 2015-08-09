package com.roide.thenotebook.recycler.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.carrotcreative.recyclercore.adapter.RecyclerCoreModel;
import com.roide.thenotebook.R;
import com.roide.thenotebook.recycler.OneEntry;
import com.roide.thenotebook.recycler.controller.DayEntryController;

import java.util.List;

/**
 * Created by roide on 8/9/15.
 */
public class DayEntryModel extends RecyclerCoreModel {

    private List<OneEntry> mEntryList;

    @Override
    public RecyclerCoreController buildController(LayoutInflater inflater, ViewGroup parent) {
        View root = inflater.inflate(R.layout.element_day_entry, parent, false);
        return new DayEntryController(root);
    }

    public DayEntryModel addEntry(List<OneEntry> list) {
        mEntryList = list;
        return this;
    }

    public List<OneEntry> getEntryList() {
        return mEntryList;
    }
}
