package com.andela.buildsdgs.rtrc.revcollector.ui.main.adaptors;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.andela.buildsdgs.rtrc.revcollector.R;
import com.andela.buildsdgs.rtrc.revcollector.models.Transaction;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.activity.TransactionDetailActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class TransactionRecyclerAdaptor extends RecyclerView.Adapter<TransactionRecyclerAdaptor.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater layoutInflater;
    private final List<Transaction> transactions;

    public TransactionRecyclerAdaptor(Context mContext, List<Transaction> transactions) {
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.transactions = transactions;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_transx_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaction transaction = transactions.get(position);
        holder.mTextCarName.setText(transaction.getVehicleName());
        holder.mTextTransxAmount.setText(transaction.getTransactionAmount());
        holder.mTextTransxTime.setText(transaction.getTransactionTime());
        holder.mCurrentPosition = position;
    }

    @Override
    public int getItemCount() {
        return transactions.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public final CircularImageView mImageVehicle;
        public final TextView mTextCarName;
        public final TextView mTextTransxTime;
        public final TextView mTextTransxAmount;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageVehicle = itemView.findViewById(R.id.image_transaction_image);
            mTextCarName = itemView.findViewById(R.id.txt_transx_car_name);
            mTextTransxTime = itemView.findViewById(R.id.txt_tranx_time);
            mTextTransxAmount = itemView.findViewById(R.id.txt_transx_amount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, TransactionDetailActivity.class);
                    intent.putExtra("position b3n",mCurrentPosition);
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
