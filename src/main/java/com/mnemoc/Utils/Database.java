package com.mnemoc.Utils;

import com.mnemoc.Models.Card;
import com.mnemoc.Models.Deck;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {

    private Connection con = null;

    public static void main(String[] args) {
        Database db = new Database();
    }

    public Database(){
        initConnection();
        setup();
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
                "did INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL);";
        String cardTable = "CREATE TABLE IF NOT EXISTS Cards (" +
                "cid INTEGER PRIMARY KEY," +
                "did INTEGER NOT NULL," +
                "front TEXT NOT NULL," +
                "back TEXT NOT NULL," +
                "last_rev INTEGER," +
                "e_factor INTEGER," +
                "next_rev INTEGER," +
                "FOREIGN KEY (did)" +
                "   REFERENCES Decks (did) );";
        try (Statement statement = con.createStatement()){
            statement.execute(deckTable);
            statement.execute(cardTable);
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    /**
     * Retrieves all the decks in an arraylist
     * @return  decks   an arraylist of type Deck containing all the decks from the table
     */
    public void getDecks(ObservableList<Deck> o) {
        String query = "SELECT * FROM Decks";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()){
                Deck dk = new Deck();
                dk.setDid(rs.getLong("DID"));
                dk.setName(rs.getString("NAME"));
                o.add(dk);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void insertDeck(Deck dk) {
        String query = "INSERT INTO Decks(did,name) VALUES (?,?)";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setLong(1, dk.getDid());
            stmt.setString(2,dk.getName());
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void insertCard(Card cd) {
        String query = "INSERT INTO Cards(cid,did,front,back) VALUES (?,?,?,?)";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setLong(1, cd.getCid());
            stmt.setLong(2,cd.getDid());
            stmt.setString(3, cd.getFront());
            stmt.setString(4, cd.getBack());
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void closeConnection(){
            try {
                con.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
    }





}