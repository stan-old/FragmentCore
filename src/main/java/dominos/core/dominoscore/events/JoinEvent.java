package dominos.core.dominoscore.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinEvent implements Listener {
    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        event.setJoinMessage("");
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] Welcome back to Fragment Cannoning!"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] Join the Discord! (/discord)"));

        //player.chat("/gm 1");

        for(Player online_players : Bukkit.getOnlinePlayers()){
            online_players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&a+&8] &f" + player.getName()));
        }

    }

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for(Player online_players : Bukkit.getOnlinePlayers()){
            online_players.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&c-&8] &f" + player.getName()));
        }
        event.setQuitMessage("");
    }

}
