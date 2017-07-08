package com.datomata;

public class Summary {
    private String summaryText;
    public Summary(String summary) {
        this.summaryText = summary;
    }

    public void setSummaryText(String value) {
        this.summaryText = value;
    }

    public String getSummaryText() {
        return this.summaryText;
    }
}
