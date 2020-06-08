package com.andela.buildsdgs.rtrc.revcollector.ui.main.fragments;

import android.content.Context;
import android.content.Intent;
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
import com.andela.buildsdgs.rtrc.revcollector.models.Transaction;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.activity.ScanQRActivity;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.adaptors.TransactionRecyclerAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;


import java.util.ArrayList;
import java.util.List;


public class TransactionFragment extends Fragment {
    private Context mContext;
    private TransactionRecyclerAdaptor recyclerAdaptor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_transactions_layout, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recycle_view_trxns);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        List<Transaction> transactionList = new ArrayList<>();
        transactionList.add(new Transaction("123", "test", "testCate", "1.0", "2020"));
        recyclerAdaptor = new TransactionRecyclerAdaptor(mContext, transactionList);
        recyclerView.setAdapter(recyclerAdaptor);
        FloatingActionButton fab = view.findViewById(R.id.fabtn_scan_qr_code);
        fab.setOnClickListener(view1 -> {
            Intent intent = new Intent(mContext, ScanQRActivity.class);
            startActivity(intent);
        });
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
        mContext = context;
    }
}
