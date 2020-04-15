package de.enrico.sql.Tables;

import de.enrico.main.LobbySystem;
import de.enrico.sql.MySQL;
import org.bukkit.Bukkit;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BankAccounts {
    private static MySQL MY_SQL = LobbySystem.getMySql();


    public static void setPlayerUUID(UUID PlayerUUID){
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"set PlayerUUID in BankAccounts of player "+ PlayerUUID);
        MY_SQL.update("INSERT INTO Bank_accounts (PlayerUUID) VALUES ('"+PlayerUUID+"')");
    }


    public static boolean isInDatabase(UUID UUID) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix+"get Player with the UUID of " + UUID);
        ResultSet rs = MY_SQL.query("SELECT * FROM Bank_accounts WHERE PlayerUUID= '" + UUID + "'");
        try {
            if (rs.next() == true) {
                return true;
            } else if (rs.next() == false) {
                return false;
            } else {
                Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix + LobbySystem.ErrorPrefix + "An Error on Bank_accounts isInDatabase");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }


}
