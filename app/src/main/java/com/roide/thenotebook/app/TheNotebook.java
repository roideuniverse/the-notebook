package com.roide.thenotebook.app;

import android.app.Application;

import com.roide.thenotebook.backend.StorageManager;

/**
 * Created by roide on 9/5/15.
 */
public class TheNotebook extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        StorageManager.initialize(getApplicationContext());
    }
}
