package dominos.core.dominoscore.features;

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

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ClearStack implements Listener {
    private List<Material> stackContents = Arrays.asList(
            Material.WHITE_CONCRETE_POWDER,
            Material.ORANGE_CONCRETE_POWDER,
            Material.MAGENTA_CONCRETE_POWDER,
            Material.LIGHT_BLUE_CONCRETE_POWDER,
            Material.YELLOW_CONCRETE_POWDER,
            Material.LIME_CONCRETE_POWDER,
            Material.PINK_CONCRETE_POWDER,
            Material.GRAY_CONCRETE_POWDER,
            Material.LIGHT_GRAY_CONCRETE_POWDER,
            Material.CYAN_CONCRETE_POWDER,
            Material.PURPLE_CONCRETE_POWDER,
            Material.BLUE_CONCRETE_POWDER,
            Material.BROWN_CONCRETE_POWDER,
            Material.GREEN_CONCRETE_POWDER,
            Material.RED_CONCRETE_POWDER,
            Material.BLACK_CONCRETE_POWDER,
            Material.WHITE_CONCRETE,
            Material.ORANGE_CONCRETE,
            Material.MAGENTA_CONCRETE,
            Material.LIGHT_BLUE_CONCRETE,
            Material.YELLOW_CONCRETE,
            Material.LIME_CONCRETE,
            Material.PINK_CONCRETE,
            Material.GRAY_CONCRETE,
            Material.LIGHT_GRAY_CONCRETE,
            Material.CYAN_CONCRETE,
            Material.PURPLE_CONCRETE,
            Material.BLUE_CONCRETE,
            Material.BROWN_CONCRETE,
            Material.GREEN_CONCRETE,
            Material.RED_CONCRETE,
            Material.BLACK_CONCRETE,
            Material.SAND,
            Material.RED_SAND,
            Material.GRAVEL,
            Material.ANVIL
    );

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
            Material item = event.getPlayer().getInventory().getItemInMainHand().getType();
            if(item.equals(Material.BONE_MEAL)){
                Player player = event.getPlayer();
                Block block = event.getClickedBlock();
                Material material = event.getClickedBlock().getType();
                if(stackContents.contains(material)){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] Removed Stack"));
                    assert block != null;
                    block.setType(Material.AIR);
                    int count = 0;
                    for(int y = 0; y < 256; y++){
                        Location newlocc = new Location(event.getClickedBlock().getWorld(), event.getClickedBlock().getX(), y, event.getClickedBlock().getZ());
                        if(stackContents.contains(newlocc.getBlock().getType())){
                            newlocc.getBlock().setType(Material.AIR);
                        }


                    }
                }
            }
        }

    }

}
