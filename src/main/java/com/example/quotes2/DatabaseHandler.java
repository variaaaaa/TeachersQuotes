package com.example.quotes2;

import java.sql.*;

public class DatabaseHandler { //database work
    private static int id;
    private static Connection connection;
    private static Statement statement;
    private static PreparedStatement prSt;

    public static int getId() {
        return id;
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

    public void MyID() throws SQLException {
        makeConnection();
        statement = connection.createStatement();
        String query = "SELECT id FROM Users";
        ResultSet findid = statement.executeQuery(query);
        while (findid.next()){
            id++;
        }
        id++;
    }

    public void signUpUser(User user) throws SQLException, ClassNotFoundException {
        makeConnection();
        String insert = "INSERT INTO Users(id, login,password,role) VALUES(?,?,?,?) ";
        prSt.executeQuery(insert);
        prSt.setString(1, Integer.toString(this.id));
        prSt.setString(2, user.getLogin());
        prSt.setString(3, user.getPassword());
        prSt.setString(4, user.getRole());
        prSt.executeUpdate();
    }

    public ResultSet getUser(User user) throws SQLException, ClassNotFoundException{
        makeConnection();
        ResultSet resSet = null;

        String select = "SELECT * FROM Users WHERE login =? AND password =?";

        PreparedStatement prSt;
        prSt = connection.prepareStatement(select);
        prSt.setString(1, user.getLogin());
        prSt.setString(2, user.getPassword());
        resSet = prSt.executeQuery();

        return resSet;

    }

    public void getAllQuotes() throws SQLException, ClassNotFoundException {
        makeConnection();
        String quotes = "SELECT * FROM Quotes";
        PreparedStatement statement = connection.prepareStatement(quotes);
        ResultSet allquotes = statement.executeQuery(quotes);
        QuotesDB database = new QuotesDB();
        while(allquotes.next()) {
            Quote q = new Quote();
            q.setId(allquotes.getInt(1));
            q.setQuote(allquotes.getString(2));
            q.setAuthor(allquotes.getString(3));
            q.setSubject(allquotes.getString(4));
            q.setDate(allquotes.getString(5));
            database.addQuote(q);
        }
    }

    public void getAllUsers() throws SQLException {
        makeConnection();
        Users users = new Users();
        String query = "SELECT * FROM Users";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while(resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt(1));
            user.setLogin(resultSet.getString(2));
            user.setPassword(resultSet.getString(3));
            user.setRole(resultSet.getString(4));
            users.addUser(user);
            users.getUsers();
            System.out.println("here");
            System.out.println(user.getLogin() + user.getId());
        }
        closeConnection();
    }

        //    public Connection getdbConnection() throws ClassNotFoundException, SQLException {
//        //getAllQuotes();
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection dbСonnection = DriverManager.getConnection(
//                "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_1920_quotes",
//                "std_1920_quotes", "passwordpassword");
//
//        Statement statement = dbСonnection.createStatement();
//        String query = "SELECT id FROM Users";
//        ResultSet findid = statement.executeQuery(query);
//        // getAllQuotes();
//        while (findid.next()) {
//            id++;
//        }
//        id++;
//        //getAllQuotes();
//        return dbСonnection;
//    }
}