package de.enrico.sql;

import de.enrico.main.LobbySystem;
import org.bukkit.Bukkit;

import java.sql.*;

public class MySQL {

    private String HOST = "";
    private int PORT = 3306;
    private String DATABASE = "";
    private String USER = "";
    private String PASSWORD = "";

    private Connection con;

    /**
     * is a method to connect to a Database with Given Data and Password
     *
     * @param HOST
     * @param PORT
     * @param DATABASE
     * @param USER
     * @param PASSWORD
     */
    public MySQL(String HOST, int PORT, String DATABASE, String USER, String PASSWORD) {
        this.HOST = HOST;
        this.PORT = PORT;
        this.DATABASE = DATABASE;
        this.USER = USER;
        this.PASSWORD = PASSWORD;

        connect();
    }

    /**
     * is a method to connect to a Database with Given Data and without a Password
     *
     * @param HOST
     * @param Port
     * @param DATABASE
     * @param USER
     */
    public MySQL(String HOST, int Port, String DATABASE, String USER) {
        this.HOST = HOST;
        this.PORT = Port;
        this.DATABASE = DATABASE;
        this.USER = USER;


        connect();
    }

    public void connect() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE + "?autoReconnect=true",
                    USER,
                    PASSWORD);
            Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"Connection established");
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+LobbySystem.ErrorPrefix+"Somthing whent wrong with the connection of your database");
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (con != null) {
                con.close();
                Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"Connection Closed !!");
            }
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+LobbySystem.ErrorPrefix+"Somthing whent wrong with the close of the database connection");
            e.printStackTrace();
        }
    }


    public void update(String qry) {
        try {
            Statement st = con.createStatement();
            st.execute(qry);
            st.close();
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
    }


    public ResultSet query(String qry) {
        ResultSet res = null;

        try {
            Statement st = con.createStatement();
            res = st.executeQuery(qry);
        } catch (SQLException e) {
            connect();
            e.printStackTrace();
        }
        return res;
    }

}
