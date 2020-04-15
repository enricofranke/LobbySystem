package de.enrico.main;

import de.enrico.events.OnJoin;
import de.enrico.sql.MySQL;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbySystem extends JavaPlugin {

    //initializes the things
    private static MySQL MY_SQL = null;
    public static String Prefix = "[LobbySystem]: ";
    public static String ErrorPrefix = "[ERROR] : ";
    FileConfiguration config = getConfig();

    @Override
    public void onEnable() {
        config.addDefault("HOST", "127.0.0.1");
        config.addDefault("Port", 3306);
        config.addDefault("DATABASE", "main");
        config.addDefault("USER", "root");
        config.addDefault("PASSWORD", "test123");
        config.options().copyDefaults(true);
        saveConfig();
        MY_SQL = new MySQL(config.getString("HOST"), 3306, "Main", "root", "f3ebffb08cef");
        checkTables();
        Bukkit.getServer().getPluginManager().registerEvents(new OnJoin(), this);
    }

    @Override
    public void onDisable() {

    }


    /**
     * is a method that checks if all needed Tables are Created
     */
    private static void checkTables() {
        MY_SQL.update("CREATE TABLE IF NOT EXISTS Players(UUID VARCHAR(36), Name VARCHAR(100), Banned boolean, FirstJoined DATETIME, LastJoined DATETIME, PRIMARY KEY (UUID))");
        MY_SQL.update("CREATE TABLE IF NOT EXISTS Modes(ID int NOT NULL AUTO_INCREMENT, Name VARCHAR(100), Status boolean, PlayerUUID VARCHAR(36), PRIMARY KEY (ID), FOREIGN KEY (PlayerUUID) REFERENCES Players(UUID))");
        MY_SQL.update("CREATE TABLE IF NOT EXISTS Bank_accounts (ID int NOT NULL AUTO_INCREMENT, type VARCHAR(20), Value int, PlayerUUID VARCHAR(36), PRIMARY KEY (ID), FOREIGN KEY (PlayerUUID) REFERENCES Players(UUID))");
        MY_SQL.update("CREATE TABLE IF NOT EXISTS Stats (ID int NOT NULL AUTO_INCREMENT, PlayerUUID VARCHAR(36), Kills int, Deaths int, GameMode VARCHAR(100), PRIMARY KEY (ID), FOREIGN KEY (PlayerUUID) REFERENCES Players(UUID))");
        MY_SQL.update("CREATE TABLE IF NOT EXISTS Shop (ID int NOT NULL AUTO_INCREMENT, Item VARCHAR(20), Price int,Name VARCHAR(40), PRIMARY KEY (ID))");
    }


    //getter and setter
    public static MySQL getMySql() {
        return MY_SQL;
    }

}


