package dominos.core.dominoscore.features;

import dominos.core.dominoscore.DominosCore;
import org.bukkit.Instrument;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Repeater;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.UUID;

//thanks to bantuerfei for making this

public class TickCounter implements Listener {

    private final DominosCore plugin;
    private HashMap<UUID, Integer> tickTracker;

    public TickCounter(DominosCore plugin) {
        this.plugin = plugin;
        this.tickTracker = new HashMap<>();
    }

    @EventHandler
    public void onRedstoneBreak(BlockBreakEvent event) {
        Player p = event.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            Block b = event.getBlock();
            if (b.getType() == Material.REPEATER) {
                UUID playerUUID = p.getUniqueId();
                Repeater rep = (Repeater) b.getBlockData();
                if (tickTracker.containsKey(playerUUID)) {
                    tickTracker.put(playerUUID, tickTracker.get(playerUUID) + rep.getDelay());
                } else {
                    tickTracker.put(playerUUID, rep.getDelay());
                }
                p.playNote(p.getLocation(), Instrument.SNARE_DRUM, new Note(tickTracker.get(p.getUniqueId()) % 25));
                showTicks(p, rep.getDelay());
            }
            else if (b.getType() == Material.COMPARATOR) {
                UUID playerUUID = p.getUniqueId();
                if (tickTracker.containsKey(playerUUID)) {
                    tickTracker.put(playerUUID, tickTracker.get(playerUUID) + 1);
                } else {
                    tickTracker.put(playerUUID, 1);
                }
                p.playNote(p.getLocation(), Instrument.SNARE_DRUM, new Note(tickTracker.get(p.getUniqueId()) % 25));
                showTicks(p, 1);
            }
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBlazeRodRightClick(PlayerInteractEvent event) {
        Action a = event.getAction();
        Player p = event.getPlayer();
        if (p.getInventory().getItemInMainHand().getType() == Material.BLAZE_ROD) {
            if (a == Action.RIGHT_CLICK_AIR || a == Action.RIGHT_CLICK_BLOCK) {
                tickTracker.put(p.getUniqueId(), 0);
                p.sendMessage("§7[§3!§7] §fReset Tick Counter");
                event.setCancelled(true);
            } else if (a == Action.LEFT_CLICK_AIR) {
                if (tickTracker.containsKey(p.getUniqueId())) {
                    showTicks(p);
                } else {
                    tickTracker.put(p.getUniqueId(), 0);
                }
            }
        }
    }

    public void showTicks(Player p, int added) {
        long ticks = tickTracker.get(p.getUniqueId());
        p.sendMessage(String.format("§7[§3i§7] §fCounter:§b+%s §7[§fGT:§b%s §fRT:§b%s §fS:§b%s§7]", added, ticks * 2, ticks, ticks / 10.0));
    }
    public void showTicks(Player p) {
        showTicks(p, 0);
    }
}
