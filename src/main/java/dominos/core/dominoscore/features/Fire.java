package dominos.core.dominoscore.features;

import dominos.core.dominoscore.DominosCore;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_16_R3.BlockPosition;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.type.Switch;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;


import java.util.HashMap;
import java.util.UUID;

//thanks to bantuerfei for making this

public class Fire implements Listener, CommandExecutor {

    private final DominosCore plugin;
    private HashMap<UUID, Block> buttons;

    public Fire(DominosCore plugin) {
        this.plugin = plugin;
        this.buttons = new HashMap<>();
    }

    private boolean pushButton(Player p, Block b){
        Switch button = (Switch) b.getBlockData(); // used to modify (turn on) the button
        if (button.isPowered()) {
            return false;
        }
        button.setPowered(true);
        b.setBlockData(button);
        BlockFace buttonFacing = button.getFacing();
        switch (button.getAttachedFace()) {
            case CEILING:
                buttonFacing = BlockFace.DOWN;
                break;
            case FLOOR:
                buttonFacing = BlockFace.UP;
                break;
        }
        Block attachedBlock = b.getRelative(buttonFacing.getOppositeFace());
        CraftWorld world = (CraftWorld) b.getWorld();
        CraftBlock tempAttached = (CraftBlock) attachedBlock;
        world.getHandle().applyPhysics(new BlockPosition(attachedBlock.getX(), attachedBlock.getY(), attachedBlock.getZ()), tempAttached.getNMS().getBlock());
        new BukkitRunnable(){
            @Override
            public void run(){
                button.setPowered(false);
                if (b.getType() == Material.STONE_BUTTON) {
                    b.setBlockData(button);
                    world.getHandle().applyPhysics(new BlockPosition(attachedBlock.getX(), attachedBlock.getY(), attachedBlock.getZ()), tempAttached.getNMS().getBlock());
                } else {
                    TextComponent message = new TextComponent("§7[§3i§7] §fButton Destroyed ");
                    TextComponent coordinates = new TextComponent(String.format("§7[§f%s, %s, %s§7]", b.getX(), b.getY(), b.getZ()));
                    coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/minecraft:tp @s %s %s %s", b.getX(), b.getY(), b.getZ())));
                    message.addExtra(coordinates);
                    p.spigot().sendMessage(message);
                }
            }
        }.runTaskLater(plugin, 20);
        return true;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            UUID playerUUID = p.getUniqueId();
            if (buttons.containsKey(playerUUID)) {
                Block b = buttons.get(playerUUID);
                TextComponent coordinates = new TextComponent(String.format("§7[§f%s, %s, %s§7]", b.getX(), b.getY(), b.getZ()));
                coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/minecraft:tp @s %s %s %s", b.getX(), b.getY(), b.getZ())));
                if (b.getType() == Material.STONE_BUTTON) {
                    if (pushButton(p, b)) {
                        TextComponent message = new TextComponent("§7[§4!§7] §fButton Pushed ");
                        message.addExtra(coordinates);
                        sender.spigot().sendMessage(message);
                    } else {
                        TextComponent message = new TextComponent("§7[§3i§7] §fButton Busy ");
                        message.addExtra(coordinates);
                        sender.spigot().sendMessage(message);
                    }
                } else {
                    TextComponent message = new TextComponent("§7[§3i§7] §fButton Missing ");
                    message.addExtra(coordinates);
                    sender.spigot().sendMessage(message);
                }
            } else {
                sender.sendMessage(String.format("§7[§3i§7] §fNo Saved Button"));
            }
        }
        return true;
    }

    @EventHandler
    public void onButtonPress(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Block b = event.getClickedBlock();
            Player p = event.getPlayer();
            if (b.getType() == Material.STONE_BUTTON) {
                buttons.put(p.getUniqueId(), b);
                TextComponent coordinates = new TextComponent(String.format("§7[§f%s, %s, %s§7]", b.getX(), b.getY(), b.getZ()));
                coordinates.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, String.format("/minecraft:tp @s %s %s %s", b.getX(), b.getY(), b.getZ())));
                TextComponent message = new TextComponent("§7[§2+§7] §fButton Saved ");
                message.addExtra(coordinates);
                p.spigot().sendMessage(message);
            }
        }
    }
}

