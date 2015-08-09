package com.roide.thenotebook.recycler.controller;

import android.view.View;
import android.widget.LinearLayout;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.roide.thenotebook.R;
import com.roide.thenotebook.recycler.model.DayEntryModel;
import com.roide.thenotebook.view.OneEntryView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by roide on 8/9/15.
 */
public class DayEntryController extends RecyclerCoreController<DayEntryModel> {

    @InjectView(R.id.element_entry_container) LinearLayout mContainer;

    public DayEntryController(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bind(DayEntryModel model) {
        int len = model.getEntryList().size();
        for(int i=0;i < 2*len; i++) {
            mContainer.addView(new OneEntryView(getContext()));
        }
        itemView.invalidate();
    }
}
