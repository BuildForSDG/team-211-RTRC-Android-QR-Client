package com.andela.buildsdgs.rtrc.revcollector.controller;

import com.andela.buildsdgs.rtrc.revcollector.models.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    public List<Transaction> getTransactionList() {
        List<Transaction> transactions = new ArrayList<>();
   // public Transaction(int transactionId, String vehicleName, String vehicleCategory, String transactionAmount, String transactionTime) {
        transactions.add(new Transaction(1, "Nissan Rogue", "4x4","GHS 1.0","12/05/2020 8:54pm"));
        transactions.add(new Transaction(2, "Toyota Corolla", "4x4","GHS .050","17/05/2020 8:07am"));
        transactions.add(new Transaction(1, "Nissan Rogue", "4x4","GHS 1.0","12/05/2020 8:54pm"));
        transactions.add(new Transaction(2, "Toyota Corolla", "4x4","GHS .050","17/05/2020 8:07am"));
        transactions.add(new Transaction(3, "Toyota Corolla", "Saloon","GHS 0.50","10/05/2020 6:21am"));
        transactions.add(new Transaction(3, "Toyota Corolla", "Saloon","GHS 0.50","10/05/2020 6:21am"));
        return transactions;
    }
}
