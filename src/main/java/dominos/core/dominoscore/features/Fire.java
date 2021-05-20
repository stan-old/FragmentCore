package dominos.core.dominoscore.features;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;
import org.bukkit.material.Lever;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class Fire implements Listener {
    public HashMap<Player, Boolean> ltoggle = new HashMap<>();
    public HashMap<Player, Location> locationbut = new HashMap<>();
    public HashMap<Player, Location> levelloc = new HashMap<>();

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block clicked = event.getClickedBlock();
            assert clicked != null;
            if (clicked.getType() == Material.STONE_BUTTON) {
                Location loc = event.getClickedBlock().getLocation();
                p.sendMessage("Button set!");
                locationbut.put(p, loc);
            }
            if (clicked.getType() == Material.LEVER) {
                BlockState state = clicked.getState();
                Lever lever = (Lever)state.getData();
                if(lever.isPowered()){
                    ltoggle.put(p, false);
                } else {
                    ltoggle.put(p, true);
                }
                Location loc = event.getClickedBlock().getLocation();
                p.sendMessage("Lever set!");
                levelloc.put(p, loc);
            }
        }
    }

    @EventHandler
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        Player p = event.getPlayer();
        if (event.getMessage().toLowerCase().equals("/fire")) {
            event.setCancelled(true);
            if(locationbut.get(p) == null){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need to click a button first"));
                return;
            }
            p.sendMessage("Button pressed!");
            BlockState state = locationbut.get(p).getBlock().getState();
            Button button = (Button)state.getData();
            button.setPowered(true);
            state.update();
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this, new Runnable() {
                public void run() {
                    BlockState state = locationbut.get(p).getBlock().getState();
                    Button button = (Button)state.getData();
                    state.getBlockData();
                    button.setPowered(false);
                    state.update();
                }
            },  20L);
        }

            if (event.getMessage().toLowerCase().equals("/lever")) {
            event.setCancelled(true);
            if(levelloc.get(p) == null){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need to click a lever first"));
                return;
            }
            p.sendMessage("Lever toggled!");
            BlockState state = levelloc.get(p).getBlock().getState();
            Lever lever = (Lever)state.getData();
            if (!ltoggle.get(p)) {
                lever.setPowered(true);
                state.update();
                ltoggle.put(p, true);
            } else if (ltoggle.get(p)) {
                lever.setPowered(false);
                state.update();
                ltoggle.put(p, false);
            }
        }
        if (event.getMessage().toLowerCase().equals("/pfire")) {
            if(locationbut.get(p) == null){
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYou need to click a button first"));
                return;
            }
            event.setCancelled(true);
            p.sendMessage("Button pressed!");
            p.chat("//paste -o");
            BlockState state = locationbut.get(p).getBlock().getState();
            Button button = (Button)state.getBlockData();
            button.setPowered(true);
            state.update();
            Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this, new Runnable() {
                public void run() {
                    BlockState state = locationbut.get(p).getBlock().getState();
                    Button button = (Button)state.getBlockData();
                    button.setPowered(false);
                    state.update();
                }
            },  20L);
        }
    }

}






