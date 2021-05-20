package dominos.core.dominoscore.features;

import dominos.core.dominoscore.DominosCore;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class Tntfill implements CommandExecutor, Listener {
    private DominosCore plugin;

    public Tntfill(DominosCore plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        Location location = player.getLocation();

        int radius = 20;
        Block middle = location.getBlock();
        for (int x = radius; x >= -radius; x--) {
            for (int y = radius; y >= -radius; y--) {
                for (int z = radius; z >= -radius; z--) {
                    if (middle.getRelative(x, y, z).getType() == Material.DISPENSER) {
                        Dispenser d = (Dispenser) middle.getRelative(x, y, z).getState();
                        d.getInventory().clear();

                        if(args.length == 1){
                            d.getInventory().addItem(new ItemStack(Material.TNT, Integer.parseInt(args[0])));
                        }else{
                            d.getInventory().addItem(new ItemStack(Material.TNT, 576));

                        }


                    }
                }
            }
        }
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',"&a[+] Filled Dispensers"));



        return true;
    }
}






