package com.andela.buildsdgs.rtrc.revcollector.models;

public class TollLocations {
    private String next;
    private String previous;
    private String count;
    private TollResults[] results;

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

    public TollResults[] getResults() {
        return results;
    }

    public void setResults(TollResults[] results) {
        this.results = results;
    }
}
