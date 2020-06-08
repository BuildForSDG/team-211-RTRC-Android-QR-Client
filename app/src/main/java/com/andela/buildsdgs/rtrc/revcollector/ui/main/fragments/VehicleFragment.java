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
import com.andela.buildsdgs.rtrc.revcollector.models.Vehicle;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.adaptors.VehicleRecyclerAdaptor;

import java.util.ArrayList;
import java.util.List;


public class VehicleFragment extends Fragment {
    private Context mContext;
    VehicleRecyclerAdaptor recyclerAdaptor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_vehicle_layout,container,false);
        RecyclerView regVehicleRecyView =  view.findViewById(R.id.recycler_view_reg_vehicles);
        LinearLayoutManager regRecyLayoutManager = new LinearLayoutManager(mContext);
        regVehicleRecyView.setLayoutManager(regRecyLayoutManager);
        List<Vehicle> vehicleList = new ArrayList<>();
        VehicleRecyclerAdaptor recyclerAdaptor = new VehicleRecyclerAdaptor(mContext,vehicleList);
        regVehicleRecyView.setAdapter(recyclerAdaptor);
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext=context;
    }
}
