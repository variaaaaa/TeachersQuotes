package com.example.quotes2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class  QuotesDB extends Quote {
    ArrayList<Quote> data;


    public QuotesDB() {
        this.data = new ArrayList<>();

    }

    public ArrayList<Quote> getQuotes() {
        return data;
    }

    public void setUsers(ArrayList<Quote>data) {
        this.data = data;
    }

    public void addQuote(Quote quote){
        data.add(quote);
    }

    public void clearQuotes() {
        data.clear();
    }
}
