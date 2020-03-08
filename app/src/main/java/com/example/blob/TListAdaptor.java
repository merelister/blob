package com.example.blob;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TListAdaptor extends RecyclerView.Adapter<TListAdaptor.tViewHolder> {

    class tViewHolder extends RecyclerView.ViewHolder {
        private final TextView tItemView;

        private tViewHolder(View itemView) {
            super(itemView);
            tItemView = itemView.findViewById(R.id.textView);
        }
    }
    private final LayoutInflater mInflater;
    private List<Transactions> transactionsList; // Cached copy of transactions

    TListAdaptor(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public tViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new tViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(tViewHolder holder, int position) {
        if (transactionsList != null) {
            Transactions current = transactionsList.get(position);
            holder.tItemView.setText(current.getAmount()); //use getter method to return info if non-public
        } else {
            // Covers the case of data not being ready yet.
            holder.tItemView.setText("No transactions");
        }
    }

    void setTransactions(List<Transactions> t){
        transactionsList = t;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // transactionsList has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (transactionsList != null)
            return transactionsList.size();
        else return 0;
    }


}
