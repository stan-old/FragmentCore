package dominos.core.dominoscore.commands;

import dominos.core.dominoscore.DominosCore;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class Discord implements CommandExecutor, Listener {
    private DominosCore plugin;

    public Discord(DominosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] https://discord.gg/TcR6YVzWHF"));

        return true;
    }
}






