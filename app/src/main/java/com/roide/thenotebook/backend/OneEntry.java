package com.roide.thenotebook.backend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.roide.thenotebook.R;
import com.roide.thenotebook.util.Util;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by roide on 8/9/15.
 */
public class OneEntry {
    private String mEntryText;
    private String mDateTime;

    public OneEntry()
    {
        mDateTime = Util.getDateString(new Date());
    }

    public String getEntryText()
    {
        return mEntryText;
    }

    public void setEntryText(String entryText)
    {
        mEntryText = entryText;
    }

    public String getDateTime()
    {
        return mDateTime;
    }

    public void setDateTime(Date date)
    {
        mDateTime = Util.getDateString(date);
    }
}
