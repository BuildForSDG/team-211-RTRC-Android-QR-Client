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
import com.andela.buildsdgs.rtrc.revcollector.services.RTRCService;
import com.andela.buildsdgs.rtrc.revcollector.services.ServiceUtil;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.activity.ScanQRActivity;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.adaptors.TransactionRecyclerAdaptor;
import com.andela.buildsdgs.rtrc.revcollector.utility.Tools;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.CaptureActivity;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


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
        Tools serviceTools = new Tools(mContext);
        RTRCService rtrcService = ServiceUtil.buildService(RTRCService.class);
        Call<Transaction> transactionCall = rtrcService.getTransactionHistory("Bearer " + serviceTools.retrieveUserProfile().getToken());
        transactionCall.enqueue(new Callback<Transaction>() {
            @Override
            public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                if (response.isSuccessful()){
                    recyclerAdaptor = new TransactionRecyclerAdaptor(mContext, response.body().getResults());
                    recyclerView.setAdapter(recyclerAdaptor);
                }else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Snackbar.make(view, "Error fetching transaction ; " + jObjError.toString(), Snackbar.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Snackbar.make(view, "Error fetching transaction ; " + e.toString(), Snackbar.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Transaction> call, Throwable t) {
                Snackbar.make(view, "Error fetching transaction ; " + t.toString(), Snackbar.LENGTH_LONG).show();
            }
        });

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
//        recyclerAdaptor.notifyDataSetChanged();
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
