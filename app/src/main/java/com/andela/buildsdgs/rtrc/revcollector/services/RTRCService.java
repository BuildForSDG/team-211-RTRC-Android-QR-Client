package com.andela.buildsdgs.rtrc.revcollector.services;

import com.andela.buildsdgs.rtrc.revcollector.models.PaymentResponse;
import com.andela.buildsdgs.rtrc.revcollector.models.TollLocations;
import com.andela.buildsdgs.rtrc.revcollector.models.Transaction;
import com.andela.buildsdgs.rtrc.revcollector.models.TransactionRequest;
import com.andela.buildsdgs.rtrc.revcollector.models.TransactionResults;
import com.andela.buildsdgs.rtrc.revcollector.models.User;
import com.andela.buildsdgs.rtrc.revcollector.models.UserDetail;
import com.andela.buildsdgs.rtrc.revcollector.models.Vehicle;
import com.andela.buildsdgs.rtrc.revcollector.utility.ServiceContants;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RTRCService {
    @POST(ServiceContants.CONTEXT_LOGIN)
    Call<UserDetail> loginUser(@Body User user);
    @GET(ServiceContants.CONTEXT_VEHICLE_DETAIL)
    Call<Vehicle> getVehicleDetail(@Header("Authorization") String bearerToken, @Path("id") String id);
    @GET(ServiceContants.CONTEXT_TOLL_LOCATIONS)
    Call<TollLocations> getTollLocation(@Header("Authorization") String bearerToken);
    @GET(ServiceContants.CONTEXT_TRANSACTION_HISTORY)
    Call<Transaction> getTransactionHistory(@Header("Authorization") String bearerToken);
    @POST(ServiceContants.CONTEXT_CONFIRM_TRANSACTION)
    Call<PaymentResponse> confirmTransaction(@Header("Authorization") String bearerToken, @Body TransactionRequest transactionRequest);
    @GET(ServiceContants.CONTEXT_TRANSACTION_DETAIL)
    Call<TransactionResults> getTransactionDetail(@Header("Authorization") String bearerToken ,@Path("id") String id);
}
