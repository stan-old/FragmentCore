package dominos.core.dominoscore.commands;

import dominos.core.dominoscore.DominosCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Features implements CommandExecutor, Listener {
    private DominosCore plugin;

    public Features(DominosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] /Tntfill - Fills Dispensers"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] /ce - Clears Nearby Sand & TNT"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] /P H - Go To Your Plot Home"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] /Discord - Discord Link"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] /Fire - Unavailable"));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] /MagicSand - Unavailable"));

        return true;
    }
}






