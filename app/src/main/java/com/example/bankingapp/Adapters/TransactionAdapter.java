package com.example.bankingapp.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bankingapp.Model.Transaction;
import com.example.bankingapp.R;

import java.util.ArrayList;

public class TransactionAdapter extends ArrayAdapter<Transaction> {

    private final Context context;
    private final int resource;

    public TransactionAdapter(Context context, int resource, ArrayList<Transaction> transactions) {
        super(context, resource, transactions);

        this.context = context;
        this.resource = resource;
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    @NonNull
    public View getView (int position, View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(resource, parent, false);
        }

        Transaction transaction = getItem(position);

        TextView txtTransactionTitle = convertView.findViewById(R.id.txt_transaction_type_id);
        TextView txtTransactionTimestamp = convertView.findViewById(R.id.txt_transaction_timestamp);
        TextView txtTransactionInfo = convertView.findViewById(R.id.txt_transaction_info);
        txtTransactionInfo.setVisibility(View.VISIBLE);
        TextView txtTransactionAmount = convertView.findViewById(R.id.txt_transaction_amount);

        txtTransactionTitle.setText(transaction.getTransactionType().toString() + " - " + transaction.getTransactionID());
        txtTransactionTimestamp.setText(transaction.getTimestamp());
        txtTransactionAmount.setText("Amount: $" + String.format("%.2f",transaction.getAmount()));

        return convertView;
    }
}