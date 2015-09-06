package com.roide.thenotebook.backend;

import android.content.Context;

import com.google.gson.Gson;
import com.roide.thenotebook.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class StorageManager
{
    private static StorageManager mInstance;
    private Context mContext;
    private int mLastId;

    private Comparator<String> mDateComparator = new Comparator<String>()
    {
        @Override
        public int compare(String strDate1, String strDate2)
        {
            Date d1 = Util.getDateFromString(strDate1);
            Date d2 = Util.getDateFromString(strDate2);
            return d1.compareTo(d2);
        }
    };

    /**
     * Key = String date
     * Value = DayEntry
     */
    private Map<String, DayEntry> mAllEntriesMap = new TreeMap<>(mDateComparator);

    private static final String TAG = StorageManager.class.getSimpleName();

    private StorageManager(Context context)
    {
        mContext = context;
        initAllEntries();
    }

    public static StorageManager initialize(Context context)
    {
        return getInstance(context);
    }

    private static StorageManager getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new StorageManager(context);
        }
        return mInstance;
    }

    public static StorageManager getInstance()
    {
        return mInstance;
    }

    public void save(DayEntry dayEntry)
    {
        for(OneEntry oneEntry: dayEntry.getEntryList())
        {
            if(oneEntry.getId() == -1)
            {
                oneEntry.setId(mLastId++);
            }
        }

        if(! mAllEntriesMap.containsKey(dayEntry.getDate()))
        {
            mAllEntriesMap.put(dayEntry.getDate(), dayEntry);
        }

        File file = new File(Util.getExternalStorageDir(mContext), dayEntry.getDate());
        Gson gson = new Gson();
        String json = gson.toJson(dayEntry);

        try
        {
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

    public DayEntry extract(File file)
    {
        Gson gson = new Gson();
        try
        {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            StringBuffer content = new StringBuffer();
            String line = null;
            while((line = bReader.readLine()) != null)
            {
                content.append(line);
            }
            DayEntry dayEntry = gson.fromJson(content.toString(), DayEntry.class);
            for(OneEntry oneEntry: dayEntry.getEntryList())
            {
                mLastId = Math.max(mLastId, oneEntry.getId());
            }

            return dayEntry;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private void initAllEntries()
    {
        File baseDir = new File(Util.getExternalStorageDir(mContext).getAbsolutePath());
        File [] allFiles = baseDir.listFiles();

        for(File file: allFiles)
        {
            mAllEntriesMap.put(file.getName(), extract(file));
        }
    }

    public Map<String, DayEntry> getAllEntries()
    {
        return mAllEntriesMap;
    }

    public DayEntry getTodayEntry()
    {
        String today = Util.getTodayDate();
        Map<String, DayEntry> allEntries = getAllEntries();

        if(allEntries.containsKey(today))
        {
            return getAllEntries().get(today);
        }

        return null;
    }

    public OneEntry getEntry(int id)
    {
        Map<String, DayEntry> entryMap = getAllEntries();
        for(DayEntry dayEntry: entryMap.values())
        {
            for(OneEntry oneEntry: dayEntry.getEntryList())
            {
                if(oneEntry.getId() == id)
                {
                    return oneEntry;
                }
            }
        }

        return null;
    }

    public DayEntry getDayEntryForOneEntry(int id)
    {
        Map<String, DayEntry> entryMap = getAllEntries();
        for(DayEntry dayEntry: entryMap.values())
        {
            for(OneEntry oneEntry: dayEntry.getEntryList())
            {
                if(oneEntry.getId() == id)
                {
                    return dayEntry;
                }
            }
        }

        return null;
    }
}