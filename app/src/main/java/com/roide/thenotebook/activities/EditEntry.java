package com.roide.thenotebook.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.roide.thenotebook.backend.DayEntry;
import com.roide.thenotebook.backend.OneEntry;
import com.roide.thenotebook.backend.StorageManager;

/**
 * Created by roide on 9/5/15.
 */
public class EditEntry extends NewEntry
{
    private static final String ARG_ENTRY_ID = "entry-id";
    private OneEntry mOneEntry;

    public static void launch(Context context, int entryId)
    {
        Intent intent = new Intent(context, EditEntry.class);
        intent.putExtra(ARG_ENTRY_ID, entryId);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        int entryId = getIntent().getIntExtra(ARG_ENTRY_ID, -1);
        if(entryId == -1)
        {
            finish();
            return;
        }

        StorageManager storageManager = StorageManager.getInstance();
        mOneEntry = storageManager.getEntry(entryId);
        mEditText.setText(mOneEntry.getEntryText());
        mEditText.setSelection(mOneEntry.getEntryText().length());
    }

    @Override
    protected void save(String content)
    {
        mOneEntry.setEntryText(content);
        StorageManager storageManager = StorageManager.getInstance();
        DayEntry dayEntry = storageManager.getDayEntryForOneEntry(mOneEntry.getId());
        storageManager.save(dayEntry);
        finish();
    }
}
