package dominos.core.dominoscore.events;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Iterator;

public class ClearStack implements Listener {
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
    if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
        Material item = event.getPlayer().getInventory().getItemInMainHand().getType();
        if(item.equals(Material.BONE_MEAL)){
            Player player = event.getPlayer();
            Block block = event.getClickedBlock();
            Material material = event.getClickedBlock().getType();
            if(material.equals(Material.SAND)||material.equals(Material.GRAVEL)||material.equals(Material.RED_SAND)){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] Removed Stack"));
                assert block != null;
                block.setType(Material.AIR);
                int count = 0;
                for(int y = 0; y < 256; y++){
                    Location newlocc = new Location(event.getClickedBlock().getWorld(), event.getClickedBlock().getX(), y, event.getClickedBlock().getZ());
                    if(newlocc.getBlock().getType().equals(Material.SAND)||newlocc.getBlock().getType().equals(Material.GRAVEL)||newlocc.getBlock().getType().equals(Material.RED_SAND)){
                        newlocc.getBlock().setType(Material.AIR);
                    }


                }
                }
        }
    }

    }

}
