package com.roide.thenotebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreAdapter;
import com.carrotcreative.recyclercore.adapter.RecyclerCoreModel;
import com.carrotcreative.recyclercore.widget.ProgressRecyclerViewLayout;
import com.getbase.floatingactionbutton.AddFloatingActionButton;
import com.roide.thenotebook.backend.DayEntry;
import com.roide.thenotebook.backend.OneEntry;
import com.roide.thenotebook.backend.StorageManager;
import com.roide.thenotebook.recycler.model.EntryModel;
import com.roide.thenotebook.recycler.model.HorizontalLineModel;
import com.roide.thenotebook.recycler.model.WeekDisplayModel;
import com.roide.thenotebook.util.Util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.main_recycler_view) ProgressRecyclerViewLayout mRecyclerViewLayout;
    @InjectView(R.id.main_fab) AddFloatingActionButton mFloatingActionButton;
    private LinearLayoutManager mLayoutManager;
    private RecyclerCoreAdapter mMainAdapter;
    private List<RecyclerCoreModel> mMainAdapterModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        prepareRecyclerView();
    }

    private void prepareRecyclerView() {
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerViewLayout.setLayoutManager(mLayoutManager);

        mMainAdapterModels = new ArrayList<>();

    }

    private void notifyDataSetChanged() {
        if(mMainAdapter == null) {
            mMainAdapter = new RecyclerCoreAdapter(mMainAdapterModels);
            mRecyclerViewLayout.setAdapter(mMainAdapter);
        }
        else {
            mMainAdapter.notifyDataSetChanged();
        }
    }

    private void reloadData()
    {
        mMainAdapterModels.clear();
        StorageManager storageManager = StorageManager.getInstance(getApplicationContext());
        String strDate = null;
        int position = 0;
        for(DayEntry entry : storageManager.getAllEntries().values())
        {
            if(strDate == null)
            {
                strDate = entry.getDate();
                mMainAdapterModels.add(new WeekDisplayModel().setDate(strDate));
            }
            else if(! strDate.equals(entry.getDate()))
            {
                if(Util.getWeekOfYear(entry.getDate()) != Util.getWeekOfYear(strDate))
                {
                    strDate = entry.getDate();
                    mMainAdapterModels.add(new WeekDisplayModel().setDate(strDate));
                }
            }
            boolean firstEntry = true;
            for(OneEntry oneEntry: entry.getEntryList())
            {
                EntryModel entryModel = new EntryModel().setEntry(oneEntry).setPosition(++position);
                entryModel.setFirstEntry(firstEntry);
                firstEntry = false;
                mMainAdapterModels.add(entryModel);
            }

            mMainAdapterModels.add(new HorizontalLineModel());
        }
        notifyDataSetChanged();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        reloadData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.main_fab)
    public void onFABClicked() {
        Toast.makeText(getApplicationContext(), "fab clicked", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MainActivity.this, NewEntry.class);
        startActivity(intent);
    }
}
