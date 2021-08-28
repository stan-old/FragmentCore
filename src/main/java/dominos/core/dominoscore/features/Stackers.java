package dominos.core.dominoscore.features;

import dominos.core.dominoscore.DominosCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class Stackers implements CommandExecutor, Listener {
    private DominosCore plugin;

    public Stackers(DominosCore plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] Received Stacker"));
        ItemStack item = new ItemStack(Material.BEACON, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f[&aStacker&f]"));
        List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Place to stack block above"), ChatColor.translateAlternateColorCodes('&', "&cAbusing this will result in a ban"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
        return true;
    }

    @EventHandler (priority = EventPriority.NORMAL)
    public void onInventoryOpen (InventoryOpenEvent event) {
        if (event.getInventory().getType() == InventoryType.BEACON) {
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onPlayerInteract(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        Material material = event.getBlock().getType();
        Block block = event.getBlock();

        if (material.equals(Material.BEACON)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] Placed Stacker"));

            new BukkitRunnable() {
                public void run() {
                    if (event.getBlock().getLocation().getBlock().getType().equals(Material.BEACON)) {
                        int y = event.getBlock().getLocation().getBlockY() - 1;
                        int uky = y+2;
                        Location toRead = new Location(event.getBlock().getWorld(), event.getBlock().getX(), uky, event.getBlock().getZ());
                        Material toStack = toRead.getBlock().getType();
                        for (int i = 0; i < y; y = y - 1) {
                            Location newlocc = new Location(event.getBlock().getWorld(), event.getBlock().getX(), y, event.getBlock().getZ());
                            Block newblock = newlocc.getBlock();

                            if (!newblock.getType().equals(Material.AIR)) {
                                if (!newblock.getLocation().add(0, +1, 0).getBlock().getType().equals(Material.BEACON)) {
                                    newblock.getLocation().add(0, +1, 0).getBlock().setType(toStack);
                                    break;
                                }
                                break;

                            }
                        }
                    }
                }

            }.runTaskTimer(this.plugin, 10, 0);






        }
    }
}






