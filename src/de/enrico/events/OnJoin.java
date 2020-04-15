package de.enrico.events;

import de.enrico.main.LobbySystem;
import de.enrico.sql.Tables.BankAccounts;
import de.enrico.sql.Tables.Modes;
import de.enrico.sql.Tables.Players;
import de.enrico.sql.Tables.Stats;
import de.enrico.utils.Item;
import de.enrico.utils.Timestamps;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.UUID;

public class OnJoin implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID PlayerUUID = player.getUniqueId();
        String PlayerName = player.getName();
        Timestamp now = Timestamps.getTime();
        checkPlayedBefor(player, PlayerUUID, PlayerName, now);
        checkBuildMode(player, PlayerUUID);

    }
    /**
     * is a method to check if a player has played before and setting him into the database
     *
     * @param player
     * @param PlayerUUID
     * @param PlayerName
     * @param now
     */
    private void checkPlayedBefor(Player player, UUID PlayerUUID, String PlayerName, Timestamp now) {
        Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix + "checking if Player " + PlayerName + " has Played Before");
        if (Players.isInDatabase(PlayerUUID)) {
            Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix + "Player with the name" + PlayerName + " has Played Before");
            Players.setPlayerName(PlayerUUID, PlayerName);
            Players.setLastJoined(PlayerUUID, now);
        }
        //checks if the player is in the Player database
        if (!Players.isInDatabase(PlayerUUID)) {
            Bukkit.getConsoleSender().sendMessage(LobbySystem.Prefix + "Player with the name " + PlayerName + "has never Played Before");
            Players.setUUID(PlayerUUID);
            Players.setPlayerName(PlayerUUID, PlayerName);
            Players.setFirstJoined(PlayerUUID, now);
            Players.setBanned(PlayerUUID, false);
            Players.setLastJoined(PlayerUUID, now);
            Modes.setMode(PlayerUUID, "BuildMode", false);
        }
        //checks if the Player is in BankAccounts
        if (!BankAccounts.isInDatabase(PlayerUUID)) {
            BankAccounts.setPlayerUUID(PlayerUUID);
        }
        //checks if the Player is in Stats
        if (!Stats.isInDatabase(PlayerUUID)) {
            Stats.setPlayerUUID(PlayerUUID);
        }

    }

    /**
     * is a method to check if the Player has BuildMode activated
     *
     * @param player
     * @param PlayerUUID
     */
    private void checkBuildMode(Player player, UUID PlayerUUID) {
        if (Modes.getModeStatus(PlayerUUID, "BuildMode") == true) {

        } else if (Modes.getModeStatus(PlayerUUID, "BuildMode") == false) {
            giveItems(player);
        } else {
            player.sendMessage(LobbySystem.Prefix + ChatColor.RED + "Etwas ist Fehlgeschlagen bitte melde dich an den Support sollte dieser fehler noch mal Auftauchen");
        }


    }

    /**
     * is a method that gives the player his Lobby Items
     *
     * @param player
     */
    private void giveItems(Player player) {
        player.getInventory().setItem(0,new Item(345, 1).setDisplayname(ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString()+"Navigator").setLore(Arrays.asList("das ist der Navigator der euch die Wichtigsten Punkte Zeigt")).build());
        player.getInventory().setItem(4,new Item(54,1 ).setDisplayname(ChatColor.GOLD+ChatColor.BOLD.toString()+"Gadgets").setLore(Arrays.asList("Hier gibt es alle gadgets die ihr auf der Lobby Benutzen Könnt")).build());
        player.getInventory().setItem(8,new Item(166,1 ).setDisplayname(ChatColor.RED+ChatColor.BOLD.toString()+"Versteckte Spieler").setLore(Arrays.asList("Hiermit kannst du alle spieler in der Lobby Verstecken")).build());
    }


}
