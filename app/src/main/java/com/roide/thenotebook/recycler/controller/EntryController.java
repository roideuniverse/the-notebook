package com.roide.thenotebook.recycler.controller;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.roide.thenotebook.R;
import com.roide.thenotebook.recycler.model.EntryModel;
import com.roide.thenotebook.view.OneEntryView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by roide on 8/9/15.
 */
public class EntryController extends RecyclerCoreController<EntryModel> {

    @InjectView(R.id.element_one_entry_text) TextView mOneEntryText;

    public EntryController(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bind(EntryModel model)
    {
        mOneEntryText.setText(model.getDayEntry().getEntryText());
    }
}
