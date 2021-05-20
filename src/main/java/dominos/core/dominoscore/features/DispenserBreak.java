package dominos.core.dominoscore.features;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DispenserBreak implements Listener {
    @EventHandler
    public void blockBreak(BlockBreakEvent e) {
        if (e.getBlock().getType().equals(Material.DISPENSER) | e.getBlock().getType().equals(Material.CHEST) | e.getBlock().getType().equals(Material.TRAPPED_CHEST)){
            e.setDropItems(false);
        }
    }
}
