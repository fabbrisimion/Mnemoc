package com.mnemoc.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    Connection con = null;

    public void initConnection(){

        try {
            String url = "jdbc:sqlite:collection.db";
            con = DriverManager.getConnection(url);
            System.out.println("Successfully connected");
        } catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public Database(){
        initConnection();
    }



}