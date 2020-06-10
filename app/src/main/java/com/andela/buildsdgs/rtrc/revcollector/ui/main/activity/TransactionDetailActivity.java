package com.andela.buildsdgs.rtrc.revcollector.ui.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.widget.NestedScrollView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.andela.buildsdgs.rtrc.revcollector.R;
import com.andela.buildsdgs.rtrc.revcollector.models.TransactionResults;
import com.andela.buildsdgs.rtrc.revcollector.services.RTRCService;
import com.andela.buildsdgs.rtrc.revcollector.services.ServiceUtil;
import com.andela.buildsdgs.rtrc.revcollector.utility.Tools;
import com.andela.buildsdgs.rtrc.revcollector.utility.ViewAnimation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TransactionDetailActivity extends AppCompatActivity {


    private View parent_view;
    TextView txtDate, txtStatus, txtDriverName, txtTransactionType, txtTransactionRef,
            txtCollectorName, txtLocation, txtAddress, txtTollReference, txtDetModelName, txtVehicleCategory,
            txtVehicleRegNum, txtAmount;

    private ImageButton bt_toggle_reviews, bt_toggle_description;
    private View lyt_expand_reviews, lyt_expand_description;
    private NestedScrollView nested_scroll_view;
    String tollID = "";
    Tools serviceTools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_detail);
        parent_view = findViewById(R.id.parent_view);
        Intent intent = getIntent();
        tollID = intent.getStringExtra("TOLL_ID");
        serviceTools = new Tools(TransactionDetailActivity.this);
        initToolbar();
        initComponent();
    }


    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Fashion");
        Tools.setSystemBarColor(this);
    }

    private void initComponent() {
        // nested scrollview
        nested_scroll_view = (NestedScrollView) findViewById(R.id.nested_scroll_view);
        // section reviews
        bt_toggle_reviews = (ImageButton) findViewById(R.id.bt_toggle_reviews);
        lyt_expand_reviews = (View) findViewById(R.id.lyt_expand_reviews);
        bt_toggle_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_reviews);
            }
        });

        // payment description
        bt_toggle_description = (ImageButton) findViewById(R.id.bt_toggle_description);
        lyt_expand_description = (View) findViewById(R.id.lyt_expand_description);
        bt_toggle_description.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toggleSection(view, lyt_expand_description);
            }
        });

        // expand first description
        toggleArrow(bt_toggle_description);
        lyt_expand_description.setVisibility(View.VISIBLE);


        //Initiatize text views and set details
        txtDetModelName = findViewById(R.id.txt_transx_detail_model_name);
        txtVehicleCategory = findViewById(R.id.txt_transx_detail_vehicle_type);
        txtVehicleRegNum = findViewById(R.id.txt_transx_detail_reistration_num);
        txtAmount = findViewById(R.id.txt_transx_detail_amount);
        txtDate = findViewById(R.id.txt_trnx_det_date);
        txtStatus = findViewById(R.id.txt_trnx_det_status);
        txtDriverName = findViewById(R.id.txt_trnx_det_driver_name);
        txtTransactionType = findViewById(R.id.txt_trnx_det_type);
        txtTransactionRef = findViewById(R.id.txt_trnx_det_reference);
        txtCollectorName = findViewById(R.id.txt_trnx_det_collector);
        txtLocation = findViewById(R.id.txt_trnx_det_location);
        txtAddress = findViewById(R.id.txt_trnx_det_address);
        txtTollReference = findViewById(R.id.txt_trnx_det_toll_ref);

        RTRCService rtrcService = ServiceUtil.buildService(RTRCService.class);
        Call<TransactionResults> transactionResultsCall = rtrcService.getTransactionDetail("Bearer " + serviceTools.retrieveUserProfile().getToken(), tollID);
        transactionResultsCall.enqueue(new Callback<TransactionResults>() {
            @Override
            public void onResponse(Call<TransactionResults> call, Response<TransactionResults> response) {
                if (response.isSuccessful()) {
                    TransactionResults result = response.body();
                    txtDetModelName.setText(result.getToll().getVehicle().getModel());
                    txtVehicleCategory.setText(result.getToll().getVehicle().getCategory().getName());
                    txtVehicleRegNum.setText(result.getToll().getVehicle().getRegistrationNumber());
                    txtAmount.setText("GHS "+ result.getAmount());
                    txtDate.setText(result.getToll().getCreated_at());
                    txtStatus.setText(result.getToll().getStatus());
                    txtDriverName.setText(result.getToll().getVehicle().getUser().getName());
                    txtTransactionType.setText(result.getTransactionType());
                    txtTransactionRef.setText(result.getReferenceCode());
                    txtCollectorName.setText(result.getToll().getCollector().getName());
                    txtLocation.setText(result.getToll().getLocation().getName());
                    txtAddress.setText(result.getToll().getLocation().getAddress());
                    txtTollReference.setText(result.getToll().getReference());

                } else {

                }
            }

            @Override
            public void onFailure(Call<TransactionResults> call, Throwable t) {

            }
        });


    }

    private void toggleSection(View bt, final View lyt) {
        boolean show = toggleArrow(bt);
        if (show) {
            ViewAnimation.expand(lyt, new ViewAnimation.AnimListener() {
                @Override
                public void onFinish() {
                    Tools.nestedScrollTo(nested_scroll_view, lyt);
                }
            });
        } else {
            ViewAnimation.collapse(lyt);
        }
    }

    public boolean toggleArrow(View view) {
        if (view.getRotation() == 0) {
            view.animate().setDuration(200).rotation(180);
            return true;
        } else {
            view.animate().setDuration(200).rotation(0);
            return false;
        }
    }

}
