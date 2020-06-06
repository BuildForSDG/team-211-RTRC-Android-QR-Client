package com.andela.buildsdgs.rtrc.revcollector.ui.main.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andela.buildsdgs.rtrc.revcollector.R;
import com.andela.buildsdgs.rtrc.revcollector.controller.TransactionController;
import com.andela.buildsdgs.rtrc.revcollector.models.Transaction;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.adaptors.TransactionRecyclerAdaptor;

import java.util.List;

public class TransactionFragment extends Fragment {
    private static final String TAG="TransactionFragment";
    private Context mContext;
    private TransactionRecyclerAdaptor recyclerAdaptor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions_layout,container,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_trxns);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        List<Transaction> transactions = new TransactionController().getTransactionList();
        System.out.println("transactionlist :"+ transactions.toString());
        recyclerAdaptor = new TransactionRecyclerAdaptor(mContext,transactions);
        recyclerView.setAdapter(recyclerAdaptor);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        recyclerAdaptor.notifyDataSetChanged();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }
}
