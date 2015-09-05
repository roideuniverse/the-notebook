package com.roide.thenotebook.backend;

import java.util.List;

/**
 * Created by roide on 8/19/15.
 */
public class DayEntry
{
    private String mDate;
    private List<OneEntry> mEntryList;

    public String getDate()
    {
        return mDate;
    }

    public List<OneEntry> getEntryList()
    {
        return mEntryList;
    }
}
