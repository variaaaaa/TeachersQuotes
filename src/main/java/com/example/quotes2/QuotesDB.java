package com.example.quotes2;

import java.util.ArrayList;

public class  QuotesDB {
    public ArrayList<String> data;

    public ArrayList<String> getData() {
        return data;
    }

    /** Name of the database */
    private static String dbName = "Quotes";

    /** Contains the names of the tables */
    private static String dbTables[]= {
            "Quotes", "Users"
    };

    public QuotesDB(){
        this.data = data;
    }


    public void AddQuotes(String line){
        data.add(line);
    }
}
