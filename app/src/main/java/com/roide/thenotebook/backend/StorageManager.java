package com.roide.thenotebook.backend;

import android.content.Context;

import com.google.gson.Gson;
import com.roide.thenotebook.util.Util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class StorageManager
{
    private static StorageManager mInstance;
    private Context mContext;
    private static final String TAG = StorageManager.class.getSimpleName();

    public StorageManager(Context context)
    {
        mContext = context;
    }

    public static StorageManager getInstance(Context context)
    {
        if(mInstance == null)
        {
            mInstance = new StorageManager(context);
        }
        return mInstance;
    }

    public void save(DayEntry dayEntry)
    {
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
            return gson.fromJson(content.toString(), DayEntry.class);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public Map<String, DayEntry> getAllEntries()
    {
        File baseDir = new File(Util.getExternalStorageDir(mContext).getAbsolutePath());
        File [] allFiles = baseDir.listFiles();

        Map<String, DayEntry>  map = new TreeMap<>();
        for(File file: allFiles)
        {
            map.put(file.getName(), extract(file));
        }
        return map;
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
}
