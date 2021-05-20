package dominos.core.dominoscore.patches;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class NetheriteBreaker implements Listener {
    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {

        Block block = event.getLocation().getBlock();
        Material material = block.getBlockData().getMaterial();

        if(!material.equals(Material.WATER) | material.equals(Material.LAVA)){
            int radius = 1;

            Block middle = event.getLocation().getBlock();
            for (int x = radius; x >= -radius; x--) {
                for (int y = radius; y >= -radius; y--) {
                    for (int z = radius; z >= -radius; z--) {
                        if (middle.getRelative(x, y, z).getType() == Material.NETHERITE_BLOCK) {
                            middle.getRelative(x, y, z).getLocation().getBlock().setType(Material.AIR);
                        }
                    }
                }
            }
        }

    }

}

