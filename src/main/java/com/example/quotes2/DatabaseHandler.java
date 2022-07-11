package com.example.quotes2;

import java.sql.*;

public class DatabaseHandler { //database work
    private static Connection connection;
    private static Statement statement;

    public int getId() {
        try {
            return MyID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void makeConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
                    "std_1920_quotes", "passwordpassword");
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection(){
        if (connection != null){
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int MyID() throws SQLException {
        int id = 0;
        makeConnection();
        statement = connection.createStatement();
        String query = "SELECT id FROM Users";
        ResultSet findid = statement.executeQuery(query);
        while (findid.next()){
            id++;
        }
        id++;
        return id;
    }

    public int QID() throws SQLException {
        int id = 0;
        makeConnection();
        statement = connection.createStatement();
        String query = "SELECT id FROM Quotes";
        ResultSet findid = statement.executeQuery(query);
        while (findid.next()){
            id++;
        }
        id++;
        System.out.println("id is " + id);
        return id;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        makeConnection();
        String insert = "INSERT INTO Users(id, login,password,role) VALUES(?,?,?,?) ";
        PreparedStatement prSt;
        prSt = connection.prepareStatement(insert);
        prSt.setString(1, Integer.toString(this.MyID()));
        prSt.setString(2, user.getLogin());
        prSt.setString(3, user.getPassword());
        prSt.setString(4, user.getRole());
        prSt.executeUpdate();
    }

    public void addQuote(Quote quote) throws SQLException, ClassNotFoundException {
        makeConnection();
        String insert = "INSERT INTO Quotes(id, quote,teacher,subject,date, userID) VALUES(?,?,?,?,?,?) ";
        PreparedStatement prSt;
        prSt = connection.prepareStatement(insert);
        prSt.setString(1, Integer.toString(this.QID()));
        prSt.setString(2, quote.getQuote());
        prSt.setString(3, quote.getTeacher());
        prSt.setString(4, quote.getSubject());
        prSt.setString(5, String.valueOf(quote.getDate()));
        prSt.setInt(6,HelloController.user.getId());
        prSt.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException{
        makeConnection();
        ResultSet resSet;
        String select = "SELECT * FROM Users WHERE login =? AND password =?";
        PreparedStatement prSt;
        prSt = connection.prepareStatement(select);
        prSt.setString(1, user.getLogin());
        prSt.setString(2, user.getPassword());
        resSet = prSt.executeQuery();
        return resSet;
    }
}