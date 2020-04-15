package de.enrico.sql.Tables;

import de.enrico.main.LobbySystem;
import de.enrico.sql.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.util.UUID;


public class Modes {

    private static MySQL MY_SQL = LobbySystem.getMySql();

    public static void setMode(UUID UUID, String Name, boolean Status) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"setMode in Modes of player "+ UUID);
        MY_SQL.update("INSERT INTO Modes (Name, Status, PlayerUUID) VALUES ('" + Name + "', " + Status + ", '" + UUID + "')");
    }

    public static void updateModeStatus(UUID UUID, String Name, boolean Status) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"updateModeStatus in BankAccounts of player "+ UUID);
        MY_SQL.update("UPDATE Modes SET Status = " + Status + " WHERE PlayerUUID = '" + UUID + "' AND Name = '" + Name + "'");
    }

    public static boolean getModeStatus(UUID UUID, String Name) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"get ModeStatus in Modes of player "+ UUID);
        Boolean result = null;
        try {
            ResultSet rs = MY_SQL.query("SELECT * FROM  Modes WHERE PlayerUUID = '" + UUID + "' AND Name = '" + Name + "'");
            if (rs.next() && Boolean.valueOf(rs.getBoolean("Status")) != null) {
                result = rs.getBoolean("Status");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}


