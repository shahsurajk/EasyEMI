package com.madscientists.easyemi.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.madscientists.easyemi.R;
import com.madscientists.easyemi.model.Loan;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Main extends AppCompatActivity {

    public static final String KEY_LOAN_DATA = "LOAN_DATA";
    private static final String LOAN_DATE_PICKER_DIALOG = "loan_date_picker";
    private static final String REPAYMENT_DATE_PICKER_DIALOG = "repayment_date_picker";
    @BindView(R.id.amountET)
    TextInputEditText amountET;
    @BindView(R.id.interestET)
    TextInputEditText interestET;
    @BindView(R.id.termET)
    TextInputEditText termET;
    @BindView(R.id.openDateBtn)
    AppCompatButton openDateBtn;
    @BindView(R.id.repaymentDayBtn)
    AppCompatButton repaymentDayBtn;
    @BindView(R.id.am_calculateEmiBtn)
    AppCompatButton calculateEmiBtn;
    private Date loanDate;
    private Date repaymentDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    public void setDateViewsBasedOnArgs(String picker_for, int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        if (picker_for.equalsIgnoreCase(LOAN_DATE_PICKER_DIALOG)) {
            openDateBtn.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
            loanDate = calendar.getTime();
        } else {
            repaymentDayBtn.setText(new StringBuilder().append(day).append("/").append(month).append("/").append(year));
            repaymentDate = calendar.getTime();
        }
    }

    private void setEditTextError(TextInputEditText editText) {
        editText.setError("This field cannot be empty!");
    }

    private boolean validateDataPoints() {
        if (TextUtils.isEmpty(amountET.getText().toString())) {
            setEditTextError(amountET);
            return false;
        } else if (TextUtils.isEmpty(interestET.getText().toString())) {
            setEditTextError(interestET);
            return false;
        } else if (TextUtils.isEmpty(termET.getText().toString())) {
            setEditTextError(termET);
            return false;
        } else if (loanDate == null) {
            Toast.makeText(this, "Select a loan date", Toast.LENGTH_LONG).show();
            return false;
        } else if (repaymentDate == null) {
            Toast.makeText(this, "Select a repayment date", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    @OnClick({R.id.openDateBtn, R.id.repaymentDayBtn, R.id.am_calculateEmiBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.openDateBtn:
                new DatePickerFragment().show(getSupportFragmentManager(), LOAN_DATE_PICKER_DIALOG);
                break;
            case R.id.repaymentDayBtn:
                new DatePickerFragment().show(getSupportFragmentManager(), REPAYMENT_DATE_PICKER_DIALOG);
                break;
            case R.id.am_calculateEmiBtn:
                if (validateDataPoints()) {
                    Loan loan = new Loan(Long.parseLong(amountET.getText().toString()),
                            Double.parseDouble(interestET.getText().toString()), Integer.parseInt(termET.getText().toString()),
                            loanDate, repaymentDate);
                    Intent intent = new Intent(Activity_Main.this, Activty_Calculations.class);
                    intent.putExtra(KEY_LOAN_DATA, loan);
                    startActivity(intent);
                }
                break;
        }
    }
}
