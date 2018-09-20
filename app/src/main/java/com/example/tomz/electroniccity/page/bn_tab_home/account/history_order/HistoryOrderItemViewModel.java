package com.example.tomz.electroniccity.page.bn_tab_home.account.history_order;

import android.annotation.SuppressLint;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tomz.electroniccity.data.model.api.membership.DataHistoryOrderResponse;
import com.example.tomz.electroniccity.helper.ToastHelper;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class HistoryOrderItemViewModel {

    public final ObservableField<String> noInvoice;
    public final ObservableField<String> dop; //date of purchase
    public final ObservableField<String> price;
    public final ObservableField<String> transactionStatus;

    public HistoryOrderItemViewModel(DataHistoryOrderResponse dataHistoryOrderResponse){
        noInvoice = new ObservableField<>(dataHistoryOrderResponse.getBstnk());
        dop = new ObservableField<>(setCustomDate(dataHistoryOrderResponse.getAudat()));
        price = new ObservableField<>(setCustomCurrency(dataHistoryOrderResponse.getNetwr()));
        transactionStatus = new ObservableField<>("Transaksi Selesai");
    }

    private String setCustomCurrency(String price){
        String formattedCurr;
        Locale localeID = new Locale("in", "ID");
        NumberFormat currencyFormat = NumberFormat.getInstance(localeID);
        formattedCurr = "Rp " + currencyFormat.format(Double.parseDouble(price));
        return formattedCurr;
    }

    @SuppressLint("SimpleDateFormat")
    private String setCustomDate(String date){
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        String newDateString = null;
        try {
            newDate = spf.parse(date);
            spf = new SimpleDateFormat("dd-MM-yyyy");
            newDateString = spf.format(newDate);
//            Log.d("newDateString tes1", newDateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDateString;
    }


    public View.OnClickListener onReadMoreClicked(){
        return view -> ToastHelper.createToast(view.getContext(), noInvoice.get(), Toast.LENGTH_LONG);
    }


}
