package de.enrico.events;

import de.enrico.main.LobbySystem;
import de.enrico.sql.Tables.Modes;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.util.UUID;

public class OnBlockPlace implements Listener {

    public void OnPlace(BlockPlaceEvent event){




    }





    private void checkBuildMode(Player player, UUID PlayerUUID) {
        if (Modes.getModeStatus(PlayerUUID, "BuildMode") == true) {

        } else if (Modes.getModeStatus(PlayerUUID, "BuildMode") == false) {

        } else {
            player.sendMessage(LobbySystem.Prefix + ChatColor.RED + "Etwas ist Fehlgeschlagen bitte melde dich an den Support sollte dieser fehler noch mal Auftauchen");
        }


    }



}
