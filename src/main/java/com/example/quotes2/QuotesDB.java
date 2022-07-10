package com.example.quotes2;

import java.util.ArrayList;

public class  QuotesDB {
    public ArrayList<Quote> data;


    public QuotesDB() {
        this.data = new ArrayList<Quote>();

    }

    public ArrayList<Quote> getQuotes() {
        return data;
    }

    public void setUsers(ArrayList<Quote> data) {
        this.data = data;
    }

    public void addQuote(Quote quote){
        data.add(quote);
    }

}
