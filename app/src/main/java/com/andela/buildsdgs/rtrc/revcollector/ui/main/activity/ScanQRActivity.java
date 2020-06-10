package com.andela.buildsdgs.rtrc.revcollector.ui.main.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.andela.buildsdgs.rtrc.revcollector.R;
import com.andela.buildsdgs.rtrc.revcollector.models.Collector;
import com.andela.buildsdgs.rtrc.revcollector.models.TollLocations;
import com.andela.buildsdgs.rtrc.revcollector.models.Transaction;
import com.andela.buildsdgs.rtrc.revcollector.models.TransactionRequest;
import com.andela.buildsdgs.rtrc.revcollector.models.Vehicle;
import com.andela.buildsdgs.rtrc.revcollector.services.RTRCService;
import com.andela.buildsdgs.rtrc.revcollector.services.ServiceUtil;
import com.andela.buildsdgs.rtrc.revcollector.utility.ServiceContants;
import com.andela.buildsdgs.rtrc.revcollector.utility.Tools;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScanQRActivity extends AppCompatActivity {

    private View parentView;
    Tools serviceTools = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_qr);
        parentView = findViewById(android.R.id.content);
        serviceTools = new Tools(ScanQRActivity.this);
        initToolbar();
        AppCompatButton scanButton = findViewById(R.id.scan_button);
        scanButton.setOnClickListener(v -> {
            scanCode();
        });
    }

    private void scanCode() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(ScanQRActivity.this);
        intentIntegrator.setCaptureActivity(CaptureQRActivity.class);
        intentIntegrator.setOrientationLocked(false);
        intentIntegrator.setCameraId(0);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning Driver's QR");
        intentIntegrator.initiateScan();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result != null) {
            if (result.getContents() != null) {
                System.out.println("QR Results : " + result.getContents());
                String vehicleId = result.getContents();

                //make API call the vehicle detail
                RTRCService rtrcService = ServiceUtil.buildService(RTRCService.class);
                Call<Vehicle> vehicleCall = rtrcService.getVehicleDetail("Bearer " + serviceTools.retrieveUserProfile().getToken(), vehicleId);
                vehicleCall.enqueue(new Callback<Vehicle>() {
                    @Override
                    public void onResponse(Call<Vehicle> call, Response<Vehicle> response) {
                        if (response.isSuccessful()) {
                            final Vehicle vehicleDetail = response.body();
                            if (vehicleDetail.isSufficientFunds()){
                                showConfirmedBalanceDialog(vehicleDetail);
                            }else {
                                showInsufficientBalanceDialog();
                            }
                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Snackbar.make(parentView, "Error fetching details ; " + jObjError.toString(), Snackbar.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Snackbar.make(parentView, "Error fetching details ; " + e.toString(), Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Vehicle> call, Throwable t) {

                    }
                });


            } else {
                Snackbar.make(parentView, "No result found...", Snackbar.LENGTH_LONG).show();
            }
        }
    }
    private void showConfirmedBalanceDialog(Vehicle vehicle) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_payment_confirm);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        TextView txtAmount = dialog.findViewById(R.id.toll_fee_confirm_payment);
        TextView txtCategory = dialog.findViewById(R.id.vehicle_category_confirm_payment);
        TextView txtModel = dialog.findViewById(R.id.vehicle_model_confirm_payment);
        TextView txtRegistration = dialog.findViewById(R.id.vehicle_registration_confirm_payment);
        txtAmount.setText(vehicle.getCategory().getTollFee());
        txtCategory.setText(vehicle.getCategory().getName());
        txtModel.setText(vehicle.getModel());
        txtRegistration.setText(vehicle.getRegistrationNumber());
        ((AppCompatButton) dialog.findViewById(R.id.btn_dialog_confirm_payment)).setOnClickListener(v -> {
            //Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_LONG).show();
            //make call to get location for collector
            RTRCService rtrcService = ServiceUtil.buildService(RTRCService.class);
            Call<TollLocations> tollLocationsCall = rtrcService.getTollLocation("Bearer " + serviceTools.retrieveUserProfile().getToken());
            tollLocationsCall.enqueue(new Callback<TollLocations>() {
                @Override
                public void onResponse(Call<TollLocations> call, Response<TollLocations> response) {

                    if (response.isSuccessful() & response.body() !=null) {
                        Collector collector = response.body().getResults()[0].getCollectors()[0];
                        if(serviceTools.retrieveUserProfile().getUser().getPk().equals(collector.getId())){
                            String vehicleId=vehicle.getId();
                            String locationId = response.body().getResults()[0].getId();
                            System.out.println("Vehicle id : " + vehicleId);
                            System.out.println("Location id : " + locationId);
                            System.out.println("Posting Transaction............." );

                            RTRCService rtrcService = ServiceUtil.buildService(RTRCService.class);
                            Call<Transaction> transactionCall = rtrcService.confirmTransaction("Bearer " + serviceTools.retrieveUserProfile().getToken(), new TransactionRequest(vehicleId,locationId));
                            transactionCall.enqueue(new Callback<Transaction>() {
                                @Override
                                public void onResponse(Call<Transaction> call, Response<Transaction> response) {
                                    if (response.isSuccessful()) {
                                        Snackbar.make(parentView, "SUCCESS", Snackbar.LENGTH_LONG).show();
                                    } else {
                                        try {
                                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                                            Snackbar.make(parentView, "Error Posting Transaction ; " + jObjError.toString(), Snackbar.LENGTH_LONG).show();
                                        } catch (Exception e) {
                                            Snackbar.make(parentView, "Error Posting Transaction ; " + e.toString(), Snackbar.LENGTH_LONG).show();
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<Transaction> call, Throwable t) {
                                    Snackbar.make(parentView, "Error Posting Transaction ; " + t.toString(), Snackbar.LENGTH_LONG).show();
                                }
                            });


                        }

                    } else {
                        try {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            Snackbar.make(parentView, "Error fetching details ; " + jObjError.toString(), Snackbar.LENGTH_LONG).show();
                        } catch (Exception e) {
                            Snackbar.make(parentView, "Error fetching details ; " + e.toString(), Snackbar.LENGTH_LONG).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<TollLocations> call, Throwable t) {

                }
            });

            dialog.dismiss();
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void showInsufficientBalanceDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
        dialog.setContentView(R.layout.dialog_insufficient_balance);
        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        ((AppCompatButton) dialog.findViewById(R.id.btn_dialog_retry_insuffient_balnce)).setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), ((AppCompatButton) v).getText().toString() + " Clicked", Toast.LENGTH_LONG).show();
            dialog.dismiss();
        });
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("QR Transaction");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Tools.setSystemBarColor(this);
    }
}
