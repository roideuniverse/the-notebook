package com.roide.thenotebook.backend;

import com.roide.thenotebook.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by roide on 8/19/15.
 */
public class DayEntry
{
    private String mDate;
    private List<OneEntry> mEntryList;

    public DayEntry()
    {
        mDate = Util.getTodayDate();
        mEntryList = new ArrayList<>();
    }

    public String getDate()
    {
        return mDate;
    }

    public List<OneEntry> getEntryList()
    {
        return mEntryList;
    }
}
