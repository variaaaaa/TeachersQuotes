package com.example.quotes2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class  QuotesDB extends Quote {
    ObservableList<Quote> data;


    public QuotesDB() {
        this.data = FXCollections.observableArrayList();

    }

    public ObservableList<Quote> getQuotes() {
        return data;
    }

    public void setUsers(ObservableList<Quote>data) {
        this.data = data;
    }

    public void addQuote(Quote quote){
        data.add(quote);
    }

    public void clearQuotes() {
        data.clear();
    }
}
