package com.roide.thenotebook.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.roide.thenotebook.R;
import com.roide.thenotebook.backend.DayEntry;
import com.roide.thenotebook.backend.OneEntry;
import com.roide.thenotebook.backend.StorageManager;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class NewEntry extends AppCompatActivity {

    @InjectView(R.id.new_entry_edit_text) EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_entry);
        ButterKnife.inject(this);
    }

    @OnClick(R.id.new_entry_save)
    public void onSaveClicked()
    {
        String content = mEditText.getText().toString();
        if(content.trim().length() > 0)
        {
            save(content);
        }
    }

    protected void save(String content)
    {
        StorageManager storageManager = StorageManager.getInstance();
        DayEntry dayEntry = storageManager.getTodayEntry();

        OneEntry oneEntry = new OneEntry();
        oneEntry.setEntryText(content);

        if(dayEntry == null)
        {
            dayEntry = new DayEntry();
        }

        dayEntry.getEntryList().add(oneEntry);
        storageManager.save(dayEntry);
        finish();
    }
}
