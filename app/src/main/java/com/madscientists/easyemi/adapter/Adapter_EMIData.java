package com.madscientists.easyemi.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.madscientists.easyemi.R;
import com.madscientists.easyemi.model.EMIData;
import com.madscientists.easyemi.model.LoanSummary;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by madscientist on 22/9/17.
 */

public class Adapter_EMIData extends RecyclerView.Adapter<Adapter_EMIData.ViewHolder> {

    private static final int VIEW_TYPE_DATA = 1;
    private static final int VIEW_TYPE_SUMMARY = 0;
    private LoanSummary loanSummary;
    private Context context;
    private List<EMIData> emiDataList;

    public Adapter_EMIData(Context context, List<EMIData> emiDataList) {
        this.context = context;
        this.emiDataList = emiDataList;
    }

    public void setLoanSummary(LoanSummary loanSummary) {
        this.loanSummary = loanSummary;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return VIEW_TYPE_SUMMARY;
        else
            return VIEW_TYPE_DATA;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layout;
        if (viewType == VIEW_TYPE_DATA) {
            layout = R.layout.row_emi_data;
        } else {
            layout = R.layout.row_emi_summary;
        }
        return new ViewHolder(LayoutInflater.from(context).inflate(layout, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_DATA) {
            EMIData emiData = emiDataList.get(position - 1);

            holder.emiAmount.setText(Html.fromHtml("EMI Amount: <b>" + String.format("%.2f", emiData.getEmi()) + "</b>"));
            holder.emiDateDay.setText(String.valueOf(emiData.getEmiDate().get(Calendar.DAY_OF_MONTH)));
            holder.emiDateMonthYear.setText(new StringBuilder().append(emiData.getEmiDate().getDisplayName(Calendar.MONTH, Calendar.SHORT
                    , Locale.getDefault())).append(" ")
                    .append(emiData.getEmiDate().get(Calendar.YEAR)));

            holder.emiDaysPast.setText(String.valueOf(emiData.getDaysCount()));
            holder.emiDCF.setText(String.format("DCF: %.2f", emiData.getDiscountedCF()));
            holder.emiDF.setText(String.format("DF: %.2f", emiData.getDiscountFactor()));
        } else {
            holder.totalAmountPaid.setText(NumberFormat.getInstance().format(loanSummary.getTotalAmountPaid()));
            holder.totalInterestPaid.setText(String.format("%.2f", loanSummary.getTotalInterestPaid()));
            holder.totalTerm.setText(String.format("%d Months", loanSummary.getTerm()));
        }
    }

    @Override
    public int getItemCount() {
        return emiDataList.size() + 1;
    }

    static

    public class ViewHolder extends RecyclerView.ViewHolder {

        @Nullable
        @BindView(R.id.emiDateDay)
        TextView emiDateDay;
        @Nullable
        @BindView(R.id.emiDateMonthYear)
        TextView emiDateMonthYear;
        @Nullable
        @BindView(R.id.emiAmount)
        TextView emiAmount;
        @Nullable
        @BindView(R.id.emiDF)
        TextView emiDF;
        @Nullable
        @BindView(R.id.emiDCF)
        TextView emiDCF;
        @Nullable
        @BindView(R.id.emiDaysPast)
        TextView emiDaysPast;

        @Nullable
        @BindView(R.id.totalAmountPaid)
        TextView totalAmountPaid;
        @Nullable
        @BindView(R.id.totalInterestPaid)
        TextView totalInterestPaid;
        @Nullable
        @BindView(R.id.totalTerm)
        TextView totalTerm;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


}
