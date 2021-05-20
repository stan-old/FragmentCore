package dominos.core.dominoscore.features;

import dominos.core.dominoscore.DominosCore;
import org.bukkit.*;
import org.bukkit.block.data.type.TNT;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.Listener;

import java.util.*;

public class ClearEntities implements CommandExecutor, Listener {
    private DominosCore plugin;

    public ClearEntities(DominosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        int count = 0;
        for(Entity entity : player.getNearbyEntities(x, y, z)) {
            count = count+1;
            if(entity instanceof TNTPrimed | entity.toString().equals("CraftFallingBlock"))
                entity.remove();
        }
        if(count<1){
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&c[+] "+count+" No Entities Found"));
        }else{
            if(count==1){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] "+count+" Entity Cleared"));
            }else{
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] "+count+" Entities Cleared"));
            }
        }

//        for(Entity en : player.getLocation().getChunk().getEntities()){
//            if(!(en instanceof Player)) {
//                en.remove();
//            }

        return true;
    }
}






