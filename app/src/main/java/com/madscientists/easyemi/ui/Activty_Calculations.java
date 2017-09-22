package com.madscientists.easyemi.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.madscientists.easyemi.R;
import com.madscientists.easyemi.adapter.Adapter_EMIData;
import com.madscientists.easyemi.model.EMIData;
import com.madscientists.easyemi.model.Loan;
import com.madscientists.easyemi.model.LoanSummary;
import com.madscientists.easyemi.util.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Activty_Calculations extends AppCompatActivity {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private Loan loan;
    private LoanSummary loanSummary;
    private List<EMIData> emiDataList = new ArrayList<>();
    private Adapter_EMIData adapter_emiData;
    private double totalEMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculations);
        ButterKnife.bind(this);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loan = getIntent().getParcelableExtra(Activity_Main.KEY_LOAN_DATA);
        initRecyclerView();
        computeEMIDateList();
    }

    private void initRecyclerView() {
        adapter_emiData = new Adapter_EMIData(this, emiDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter_emiData);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    private void computeLoanSummary() {
        loanSummary = new LoanSummary((long) totalEMI, (float) (totalEMI - loan.getAmount()), loan.getAmount(),
                loan.getTerm());
        adapter_emiData.setLoanSummary(loanSummary);
    }

    private void computeEMIDateList() {
        for (int i = 0; i < loan.getTerm(); i++) {
            EMIData emiData = new EMIData();
            Date nextDate = Utils.addMonthsToDate(loan.getRepaymentDate(), i);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(nextDate);
            emiData.setEmiDate(calendar);
            emiData.setEmi(Utils.calculateEMIByPMT(loan.getAmount(), loan.getRate(), loan.getTerm()));
            emiData.setDaysCount(Utils.getDaysDifference(loan.getOpenDate(), nextDate));
            emiData.setDiscountFactor(Utils.getDiscountFactor(loan.getRate(), emiData.getDaysCount()));
            emiData.setDiscountedCF(Utils.getDiscountedCashFlow(emiData.getEmi(), emiData.getDiscountFactor()));
//            Log.i(Activty_Calculations.class.getCanonicalName().toString(), "computeEMIDateList: "+emiData.toString());
            totalEMI += emiData.getEmi();
            emiDataList.add(emiData);
        }
        computeLoanSummary();
        adapter_emiData.notifyDataSetChanged();
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }
}
