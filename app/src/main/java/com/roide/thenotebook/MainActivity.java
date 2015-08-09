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
import com.roide.thenotebook.recycler.OneEntry;
import com.roide.thenotebook.recycler.model.DayEntryModel;

import java.util.ArrayList;
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
        ArrayList<OneEntry> oneEntry = new ArrayList();
        oneEntry.add(new OneEntry());
        mMainAdapterModels.add(new DayEntryModel().addEntry(oneEntry));
        notifyDataSetChanged();
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
