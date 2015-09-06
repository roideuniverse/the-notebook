package com.roide.thenotebook.recycler.controller;

import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.roide.thenotebook.R;
import com.roide.thenotebook.activities.EditEntry;
import com.roide.thenotebook.recycler.model.EntryModel;
import com.roide.thenotebook.util.Util;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by roide on 8/9/15.
 */
public class EntryController extends RecyclerCoreController<EntryModel> {

    @InjectView(R.id.element_one_entry_text) TextView mOneEntryText;
    @InjectView(R.id.element_one_entry_week_day) TextView mDayTextView;
    @InjectView(R.id.element_one_entry_week_day_name) TextView mDayNameTextView;
    @InjectView(R.id.element_one_entry_week_container) LinearLayout mEntryWeekContainer;

    public EntryController(View itemView) {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bind(EntryModel model)
    {
        mOneEntryText.setText(model.getEntry().getEntryText());
        if(model.getPosition() % 2 == 0)
        {
            mOneEntryText.setBackgroundColor(Color.parseColor("#2196F3"));
            mOneEntryText.setTextColor(Color.WHITE);
        }
        else
        {
            mOneEntryText.setBackgroundColor(Color.WHITE);
            mOneEntryText.setTextColor(Color.BLACK);
        }

        bindEntryDate(model);
        bindOnClick(model);
    }

    private void bindEntryDate(EntryModel entryModel)
    {
        if(entryModel.isFirstEntry())
        {
            mEntryWeekContainer.setVisibility(View.VISIBLE);
            mDayTextView.setText(Util.getDay(entryModel.getEntry().getDateTime()));
            mDayNameTextView.setText(Util.getWeekDayName(entryModel.getEntry().getDateTime()));
        }
        else
        {
            mEntryWeekContainer.setVisibility(View.INVISIBLE);
        }
    }

    private void bindOnClick(final EntryModel model)
    {
        itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditEntry.launch(getContext(), model.getEntry().getId());
            }
        });
    }
}
