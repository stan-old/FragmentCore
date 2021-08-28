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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MagicConc implements CommandExecutor, Listener {
    private DominosCore plugin;

    public MagicConc(DominosCore plugin) {
        this.plugin = plugin;
    }

    private List<Material> concreteColors = Arrays.asList(
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
            Material.BLACK_CONCRETE_POWDER
    );

    private Random rand = new Random();


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] Received MagicConc"));
        ItemStack item = new ItemStack(Material.SHROOMLIGHT, 1);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&f[&aMagicConc&f]"));
        List<String> lore = Arrays.asList(ChatColor.translateAlternateColorCodes('&', "&7Place to generate concrete powder"), ChatColor.translateAlternateColorCodes('&', "&cAbusing this will result in a ban"));
        meta.setLore(lore);
        item.setItemMeta(meta);
        player.getInventory().addItem(item);
        return true;
    }

    @EventHandler
    public void onPlayerInteract(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        ItemMeta im = player.getInventory().getItemInMainHand().getItemMeta();
        Material material = event.getBlock().getType();
        Block block = event.getBlock();

        if (material.equals(Material.SHROOMLIGHT)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a[+] Placed MagicConc"));

            new BukkitRunnable() {
                public void run() {
                    if (event.getBlock().getLocation().getBlock().getType().equals(Material.SHROOMLIGHT)) {
                        int y = event.getBlock().getLocation().getBlockY() - 1;
                        for (int i = 0; i < y; y = y - 1) {
                            Location newlocc = new Location(event.getBlock().getWorld(), event.getBlock().getX(), y, event.getBlock().getZ());
                            Block newblock = newlocc.getBlock();

                            if (!newblock.getType().equals(Material.AIR)) {
                                if (!newblock.getLocation().add(0, +1, 0).getBlock().getType().equals(Material.SHROOMLIGHT)) {
                                    newblock.getLocation().add(0, +1, 0).getBlock().setType(concreteColors.get(rand.nextInt(concreteColors.size())));
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






