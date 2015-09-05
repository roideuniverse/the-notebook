package com.roide.thenotebook.recycler.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.carrotcreative.recyclercore.adapter.RecyclerCoreModel;
import com.roide.thenotebook.R;
import com.roide.thenotebook.backend.DayEntry;
import com.roide.thenotebook.backend.OneEntry;
import com.roide.thenotebook.recycler.controller.EntryController;

/**
 * Created by roide on 8/9/15.
 */
public class EntryModel extends RecyclerCoreModel {

    private OneEntry mOneEntry;

    @Override
    public RecyclerCoreController buildController(LayoutInflater inflater, ViewGroup parent) {
        View root = inflater.inflate(R.layout.elelement_one_entry, parent, false);
        return new EntryController(root);
    }

    public EntryModel setEntry(OneEntry oneEntry) {
        mOneEntry = oneEntry;
        return this;
    }

    public OneEntry getDayEntry() {
        return mOneEntry;
    }
}
