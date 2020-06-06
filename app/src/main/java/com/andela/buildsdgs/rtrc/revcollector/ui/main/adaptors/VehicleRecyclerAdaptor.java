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
import com.andela.buildsdgs.rtrc.revcollector.models.Vehicle;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.activity.VehicleDetailActivity;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;


public class VehicleRecyclerAdaptor extends RecyclerView.Adapter<VehicleRecyclerAdaptor.ViewHolder>{

    private final Context mContext;
    private final LayoutInflater layoutInflater;
    private final List<Vehicle> vehicles;

    public VehicleRecyclerAdaptor(Context mContext,List<Vehicle> vehicles ) {
        this.mContext = mContext;
        this.layoutInflater = LayoutInflater.from(mContext);
        this.vehicles=vehicles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item_reg_vehicle_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        holder.mTextVehicleName.setText(vehicle.getVehicleName());
        holder.mTextVehicleCategory.setText(vehicle.getVehicleCategory());
        holder.mPosition=position;
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public final CircularImageView mImageVehicle;
        public final TextView mTextVehicleName;
        public final TextView mTextVehicleCategory;
        public int mPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageVehicle = itemView.findViewById(R.id.image_reg_vehicle_image);
            mTextVehicleName = itemView.findViewById(R.id.txt_reg_vehicle_name);
            mTextVehicleCategory = itemView.findViewById(R.id.txt_reg_vehicle_category);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, VehicleDetailActivity.class);
                    intent.putExtra("position b3n",mPosition);
                    mContext.startActivity(intent);
                }
            });

        }
    }
}
