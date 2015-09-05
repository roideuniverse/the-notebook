package com.roide.thenotebook.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.roide.thenotebook.R;
import com.roide.thenotebook.backend.OneEntry;

/**
 * Created by roide on 8/9/15.
 */
public class OneEntryView extends FrameLayout {

    private TextView mEntryText;

    public OneEntryView(Context context) {
        super(context);
        inflateView();
    }

    public OneEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateView();
    }

    public OneEntryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView();
    }

    private void inflateView() {
        LayoutInflater.from(getContext()).inflate(R.layout.elelement_one_entry, this);
        mEntryText = (TextView) findViewById(R.id.element_one_entry_text);
    }

    public void set(OneEntry oneEntry)
    {
        mEntryText.setText(oneEntry.getEntryText());
    }
}
