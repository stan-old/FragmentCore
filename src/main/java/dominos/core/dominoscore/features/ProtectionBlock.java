package dominos.core.dominoscore.features;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

import java.util.Iterator;

public class ProtectionBlock implements Listener {
    @EventHandler

    public void onEntityExplode(EntityExplodeEvent e) {

        if (e.getEntity().getType() == EntityType.PRIMED_TNT) {
            Iterator<Block> iterator = e.blockList().iterator();
                while(iterator.hasNext())
                {
                    Block b = iterator.next();

                        int radius = 256;

                        for (int y = radius; y >= -radius; y--) {

                            Block middle = b.getLocation().getBlock();
                            Material materialtype = middle.getRelative(0,y, 0).getType();

                            if(materialtype.equals(Material.EMERALD_BLOCK)){
                                iterator.remove();
                                break;
                            }
                        }
                }


        }

    }
}