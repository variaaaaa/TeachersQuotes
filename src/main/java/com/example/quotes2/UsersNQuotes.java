package com.example.quotes2;

public class UsersNQuotes {

    public User user;
    public Quote quote;

    public UsersNQuotes(User user, Quote quote){
        this.user = user;
        this.quote = quote;
    }

    public User getUser() {
        return user;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UsersNQuotes(){
    }
}
