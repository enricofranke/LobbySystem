package de.enrico.sql.Tables;

import de.enrico.main.LobbySystem;
import de.enrico.sql.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class Stats {

    private static MySQL MY_SQL = LobbySystem.getMySql();

    public static void setPlayerUUID(UUID PlayerUUID){
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set PlayerUUID in Stats of player "+ PlayerUUID);
        MY_SQL.update("INSERT INTO Stats(PlayerUUID) VALUES('"+PlayerUUID+"');");
    }

    public static void setKills(UUID PlayerUUID, int Kills){
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set Kills in Stats of player "+ PlayerUUID);
        MY_SQL.update("UPDATE Stats SET Kills =  '" + Kills + "' WHERE UUID = '" + PlayerUUID + "'");
    }
    public static void setDeaths(UUID PlayerUUID, int Deaths){
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set Deaths in Stats of player "+ PlayerUUID);
        MY_SQL.update("UPDATE Stats SET Deaths =  '" + Deaths + "' WHERE UUID = '" + PlayerUUID + "'");
    }
    public static void setGameMode(UUID PlayerUUID, String GameMode){
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set GameMode in Stats of player "+ PlayerUUID);
        MY_SQL.update("UPDATE Stats SET GameMode = '"+GameMode+"'WHERE UUID = '"+PlayerUUID+"'" );
    }

    public static boolean isInDatabase(UUID UUID) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"get isInDatabase in Stats of player "+ UUID);
        ResultSet rs = MY_SQL.query("SELECT * FROM Stats WHERE PlayerUUID= '" + UUID + "'");
        try {
            if (rs.next() == true) {
                return true;
            } else if (rs.next() == false) {
                return false;
            } else {
                Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix + LobbySystem.ErrorPrefix + "An Error on Stats isInDatabase");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }




}
