package com.roide.thenotebook.recycler.controller;

import android.view.View;
import android.widget.TextView;

import com.carrotcreative.recyclercore.adapter.RecyclerCoreController;
import com.roide.thenotebook.R;
import com.roide.thenotebook.recycler.model.WeekDisplayModel;
import com.roide.thenotebook.util.Util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by roide on 9/5/15.
 */
public class WeekDisplayController extends RecyclerCoreController<WeekDisplayModel>
{
    @InjectView(R.id.element_week_display) TextView mTextView;

    public WeekDisplayController(View itemView)
    {
        super(itemView);
        ButterKnife.inject(this, itemView);
    }

    @Override
    public void bind(WeekDisplayModel model)
    {
        mTextView.setText(parseStrDate(model.getStrDate()));
    }

    private String parseStrDate(String strDate)
    {
        int dayOfWeek = Util.getDayOfWeek(strDate);
        /**
         * Saturday is 7th day and Sunday 1st day, so decrement
         */
        dayOfWeek--;
        if(dayOfWeek != 0)
        {
            Date date = Util.getDateFromString(strDate);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DATE, - dayOfWeek);
            date.setTime(calendar.getTime().getTime());
            strDate = Util.getDateString(date);
        }
        String firstDateOfWeek = strDate;
        String lastDayOfWeek = getLastDayOfWeek(firstDateOfWeek);
        String label = trim(firstDateOfWeek) + " - " + trim(lastDayOfWeek);
        return label;
    }

    private String getLastDayOfWeek(String firstDateOfWeek)
    {
        Date date = Util.getDateFromString(firstDateOfWeek);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, 7);
        date.setTime(calendar.getTime().getTime());
        return Util.getDateString(date);
    }

    private String trim(String strDate)
    {
        Date date = Util.getDateFromString(strDate);
        String val = new SimpleDateFormat("MMM dd").format(date);
        return val;
    }
}
