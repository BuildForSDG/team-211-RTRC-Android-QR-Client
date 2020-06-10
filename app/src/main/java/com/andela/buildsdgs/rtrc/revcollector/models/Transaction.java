package com.andela.buildsdgs.rtrc.revcollector.models;

import java.util.List;

public class Transaction {
    private String next;
    private String previous;
    private String count;
    private List<TransactionResults> results;

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public List<TransactionResults> getResults() {
        return results;
    }

    public void setResults(List<TransactionResults> results) {
        this.results = results;
    }
}
