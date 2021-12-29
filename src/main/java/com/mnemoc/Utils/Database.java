package com.mnemoc.Utils;

import com.mnemoc.Models.Card;
import com.mnemoc.Models.Deck;
import javafx.collections.ObservableList;

import java.sql.*;

public class Database {

    private Connection con = null;
    private static int n = 10;

    public Database(){
        initConnection();
        setup();
    }

    private void initConnection(){
        try {
            Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:collection.db";
            con = DriverManager.getConnection(url);
        } catch (ClassNotFoundException c){
            c.printStackTrace();
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    private Connection getConnection(){
        Connection connection = null;

        try {
            String url = "jdbc:sqlite:collection.db";
            connection = DriverManager.getConnection(url);
        } catch (SQLException s) {
            s.printStackTrace();
        }
        return connection;
    }

    private void setup(){
        String deckTable = "CREATE TABLE IF NOT EXISTS Decks (" +
                "did INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL);";
        String cardTable = "CREATE TABLE IF NOT EXISTS Cards (" +
                "cid INTEGER PRIMARY KEY," +
                "did INTEGER NOT NULL," +
                "front TEXT NOT NULL," +
                "back TEXT NOT NULL," +
                "last_rev INTEGER," +
                "e_factor REAL," +
                "next_rev INTEGER," +
                "reps INTEGER," +
                "intrvl INTEGER," +
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

    public void countNewCardsToday(Deck dk){
        int count = 0;
        String query = "SELECT count(*) FROM Cards WHERE did = " + dk.getDid() +
                " AND strftime('%Y-%m-%d', last_rev / 1000, 'unixepoch') == date('now') ORDER BY cid;";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)){
            count =  rs.getInt(1);
        } catch (SQLException se){
            se.printStackTrace();
        }

        if(count>=n) n = 0;
        else n = n-count;
    }

    public void getNewCards(Deck dk, ObservableList<Card> o){
        String query = "SELECT * FROM Cards WHERE DID = " + dk.getDid() +
                " AND last_rev IS NULL ORDER BY cid ASC LIMIT " + n + ";";
        try (Statement stmt = con.createStatement();ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()){
                Card cd = new Card();
                cd.setDid(rs.getLong("DID"));
                cd.setCid(rs.getLong("CID"));
                cd.setFront(rs.getString("FRONT"));
                cd.setBack(rs.getString("BACK"));
                cd.setLastRev(rs.getLong("LAST_REV"));
                cd.setENr(rs.getFloat("E_FACTOR"));
                cd.setReps(rs.getLong("REPS"));
                cd.setIntrvl(rs.getLong("INTRVL"));
                o.add(cd);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void getScheduledCards(Deck dk, ObservableList<Card> o){
        String query = "SELECT * FROM Cards WHERE DID = " + dk.getDid() +
                " AND strftime('%Y-%m-%d', next_rev / 1000, 'unixepoch') == date('now');";
        try (Statement stmt = con.createStatement();ResultSet rs = stmt.executeQuery(query)){
            while (rs.next()){
                Card cd = new Card();
                cd.setDid(rs.getLong("DID"));
                cd.setCid(rs.getLong("CID"));
                cd.setFront(rs.getString("FRONT"));
                cd.setBack(rs.getString("BACK"));
                cd.setLastRev(rs.getLong("LAST_REV"));
                cd.setENr(rs.getFloat("E_FACTOR"));
                cd.setReps(rs.getLong("REPS"));
                cd.setIntrvl(rs.getLong("INTRVL"));
                o.add(cd);
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }

    }

    public void getCards(Deck dk, ObservableList<Card> o){
        countNewCardsToday(dk);
        getNewCards(dk, o);
        getScheduledCards(dk, o);
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

    //The card passed as argument will be already scheduled
    public void updateCard(Card cd){
        String query = "UPDATE Cards SET e_factor=?, next_rev=?, last_rev=?, reps=?, intrvl=? WHERE cid=? ;";
        try (PreparedStatement stmt = con.prepareStatement(query)){
            stmt.setFloat(1,cd.getENr());
            stmt.setLong(2, cd.getNextRev());
            stmt.setLong(3, cd.getLastRev());
            stmt.setLong(4, cd.getReps());
            stmt.setLong(5, cd.getIntrvl());
            stmt.setLong(6, cd.getCid());
            stmt.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void remove(Deck dk){
        long did = dk.getDid();
        String deck = "DELETE FROM Decks WHERE did = ?";
        String cards = "DELETE FROM Cards WHERE did = ?";
        try(PreparedStatement stmt1 = con.prepareStatement(deck);
            PreparedStatement stmt2 = con.prepareStatement(cards)){
            stmt1.setLong(1, did);
            stmt1.addBatch();

            stmt2.setLong(1,did);
            stmt2.addBatch();

            stmt1.executeBatch();
            stmt2.executeBatch();
        } catch (SQLException se){
            se.printStackTrace();
        }
    }

    private void closeConnection(Connection connection){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}