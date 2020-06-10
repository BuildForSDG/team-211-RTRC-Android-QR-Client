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
import com.andela.buildsdgs.rtrc.revcollector.models.TransactionResults;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.activity.TransactionDetailActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

public class TransactionRecyclerAdaptor extends RecyclerView.Adapter<TransactionRecyclerAdaptor.ViewHolder> {

    private final Context mContext;
    private final LayoutInflater layoutInflater;
    private final List<TransactionResults> transactions;

    public TransactionRecyclerAdaptor(Context mContext, List<TransactionResults> transactions) {
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
        TransactionResults transaction = transactions.get(position);
        String[] transactionTimes = transaction.getCreatedAt().split("T");
        String[] times = transactionTimes[1].split("\\.");
        holder.mTextCarName.setText(transaction.getToll().getVehicle().getModel());
        holder.mTextTransxTime.setText(times[0]);
        holder.mTextTransxRegNumt.setText(transaction.getToll().getVehicle().getRegistrationNumber());
        holder.mTextTransxVehType.setText(transaction.getToll().getVehicle().getCategory().getName());
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
        public final TextView mTextTransxRegNumt;
        public final TextView mTextTransxVehType;
        public int mCurrentPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageVehicle = itemView.findViewById(R.id.trnx_list_image);
            mTextCarName = itemView.findViewById(R.id.txt_trnx_list_model);
            mTextTransxTime = itemView.findViewById(R.id.txt_transx_list_datetime);
            mTextTransxRegNumt = itemView.findViewById(R.id.txt_transx_list_reg_number);
            mTextTransxVehType = itemView.findViewById(R.id.txt_tranx_list_vehicle_type);

            itemView.setOnClickListener(v -> {
                TransactionResults transaction = transactions.get(mCurrentPosition);
                Intent intent = new Intent(mContext, TransactionDetailActivity.class);
                intent.putExtra("TOLL_ID",transaction.getId());
                mContext.startActivity(intent);
            });
        }
    }
}
