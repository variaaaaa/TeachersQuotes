package com.example.quotes2;

import java.sql.*;

public class DatabaseHandler { //database work
    private static int id;

    public static int getId() {
        return id;
    }


    public Connection getdbConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection dbСonnection = DriverManager.getConnection(
                "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                "std_1920_quotes", "passwordpassword");

        String quotes = "SELECT * FROM Quotes";
        Statement statement = dbСonnection.createStatement();
        ResultSet allquotes = statement.executeQuery(quotes);
        QuotesDB forDB = new QuotesDB();
        while(allquotes.next()) {
            int id = allquotes.getInt("id");
            String quote = allquotes.getString("quote");
            String teacher = allquotes.getString("teacher");
            String subject = allquotes.getString("subject");
            String date = allquotes.getString("date");

            System.out.println(id + quote + teacher + subject + date);
        }

        String query = "SELECT id FROM Users";
        ResultSet findid = statement.executeQuery(query);
        while (findid.next()) {
            id++;
        }
        id++;
        return dbСonnection;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {

        String insert = "INSERT INTO Users(id, login,password,role) VALUES(?,?,?,?) ";
        PreparedStatement prSt = getdbConnection().prepareStatement(insert);
        prSt.setString(1, Integer.toString(this.id));
        prSt.setString(2, user.getLogin());
        prSt.setString(3, user.getPassword());
        prSt.setString(4, user.getRole());
        prSt.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException{
        ResultSet resSet = null;

        String select = "SELECT * FROM Users WHERE login =? AND password =?";

        PreparedStatement prSt = getdbConnection().prepareStatement(select);
        prSt.setString(1, user.getLogin());
        prSt.setString(2, user.getPassword());
        resSet = prSt.executeQuery();

        return resSet;

    }
}