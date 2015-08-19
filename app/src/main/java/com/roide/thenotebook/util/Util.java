package com.roide.thenotebook.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.roide.thenotebook.R;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by roide on 8/19/15.
 */
public class Util
{
    private static final String TAG = Util.class.getSimpleName();
    private static final String DATE_FORMAT = "yyyy_MM_dd";

    private Util()
    {

    }

    public static String getAppName(Context context)
    {
        return context.getResources().getString(R.string.app_name);
    }

    public static File getExternalStorageDir(Context context)
    {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                getAppName(context));

        if (!file.mkdirs()) {
            Log.e(TAG, "Directory not created");
        }
        return file;
    }

    public static String getTodayDate()
    {
        return getDateString(new Date());
    }

    public static String getDateString(Date date)
    {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(date);
    }

    public static Date getDateFromString(String dateStr)
    {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        try
        {
            return dateFormat.parse(dateStr);
        }
        catch(ParseException e)
        {
            e.printStackTrace();
        }
        return new Date();
    }
}
