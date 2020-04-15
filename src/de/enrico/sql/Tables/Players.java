package de.enrico.sql.Tables;

import de.enrico.main.LobbySystem;
import de.enrico.sql.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;


public class Players {

    private static MySQL MY_SQL = LobbySystem.getMySql();

    public static void setPlayerName(UUID UUID, String Name) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set PlayerName in Players of player "+ UUID);
        MY_SQL.update("UPDATE Players SET Name =  '" + Name + "' WHERE UUID = '" + UUID + "'");
    }

    public static void setFirstJoined(UUID UUID, Timestamp timestamp) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set FirstJoined in Players of player "+ UUID);
        MY_SQL.update("UPDATE Players SET FirstJoined =  '" + timestamp + "' WHERE UUID = '" + UUID + "'");
    }

    public static void setLastJoined(UUID UUID, Timestamp deinemama) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set LastJoined in Players of player "+ UUID);
        MY_SQL.update("UPDATE Players SET LastJoined =  '" + deinemama + "' WHERE UUID = '" + UUID + "'");

    }

    public static void setBanned(UUID UUID, boolean isBanned) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set Banned in Players of player "+ UUID);
        MY_SQL.update("UPDATE Players SET Banned =  " + isBanned + " WHERE UUID = '" + UUID + "'");

    }

    public static boolean isInDatabase(UUID UUID) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"get isInDatabase in Players of player "+ UUID);
        ResultSet rs = MY_SQL.query("SELECT * FROM Players WHERE UUID= '" + UUID + "'");
        try {
            if (rs.next() == true) {
                return true;
            } else if (rs.next() == false) {
                return false;
            } else {
                Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix + LobbySystem.ErrorPrefix + "An Error on Players isInDatabase");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


    public static void setUUID(UUID UUID) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set UUID in Players of player "+ UUID);
        MY_SQL.update("INSERT INTO Players (UUID) VALUES ('" + UUID + "');");
    }
}


