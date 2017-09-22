package com.madscientists.easyemi.ui;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by madscientist on 21/9/17.
 */

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

//    private static final String KEY_PICKER_FOR = "picker_for";

    private Calendar calendar = Calendar.getInstance();
    private int month = calendar.get(Calendar.MONTH);
    private int year = calendar.get(Calendar.YEAR);
    private int day = calendar.get(Calendar.DAY_OF_MONTH);

    /*public static DatePickerFragment getInstance(int picker_for) {
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        Bundle bundle = new Bundle(1);
        bundle.putInt(KEY_PICKER_FOR, picker_for);
        datePickerFragment.setArguments(bundle);
        return datePickerFragment;
    }*/
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        ((Activity_Main) getActivity()).setDateViewsBasedOnArgs(getTag(), i, i1, i2);
    }
}
