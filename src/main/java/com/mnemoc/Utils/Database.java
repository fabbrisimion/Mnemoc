package com.mnemoc.Utils;

import com.mnemoc.Models.Deck;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private Connection con = null;

    public static void main(String[] args) {
        Database db = new Database();
    }

    public void initConnection(){

        try {
            String url = "jdbc:sqlite:collection.db";
            con = DriverManager.getConnection(url);
            System.out.println("Successfully connected");
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public Connection getConnection(){
        return con;
    }

    public void setup(){

        String deckTable = "CREATE TABLE IF NOT EXISTS Decks (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL)";
        try (Statement statement = con.createStatement()){
            statement.execute(deckTable);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ArrayList<Deck> getDecks() {
        ArrayList<Deck> decks = new ArrayList<>();
        String query = "SELECT * FROM Decks";
        try (Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()){

            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
        return decks;
    }

    public void closeConnection() throws SQLException{
            con.close();
    }

    public Database(){
        initConnection();
        setup();
    }



}