package dominos.core.dominoscore;

import dominos.core.dominoscore.commands.Discord;
import dominos.core.dominoscore.commands.Features;
import dominos.core.dominoscore.features.ClearStack;
import dominos.core.dominoscore.events.JoinEvent;
import dominos.core.dominoscore.features.*;
import dominos.core.dominoscore.patches.EastWest;
import dominos.core.dominoscore.patches.MovementFix;
import dominos.core.dominoscore.patches.NetheriteBreaker;
import dominos.core.dominoscore.features.MagicSand;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class DominosCore extends JavaPlugin {

    private static DominosCore instance;
    private Fire fire;
    private Tracker tracker;
    public static DominosCore getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        fire = new Fire(this);
        tracker = new Tracker(this);

        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new NetheriteBreaker(), this);
        getServer().getPluginManager().registerEvents(new DispenserBreak(), this);
        getServer().getPluginManager().registerEvents(new ProtectionBlock(), this);
        //getServer().getPluginManager().registerEvents(new MovementFix(), this);
        //getServer().getPluginManager().registerEvents(new EastWest(), this);
        getServer().getPluginManager().registerEvents(new MagicSand(this), this);
        getServer().getPluginManager().registerEvents(new MagicConc(this), this);
        getServer().getPluginManager().registerEvents(new Stackers(this), this);
        getServer().getPluginManager().registerEvents(new ClearStack(), this);
        getServer().getPluginManager().registerEvents(fire, this);
        getServer().getPluginManager().registerEvents(new TickCounter(this), this);
        getServer().getPluginManager().registerEvents(tracker, this);


        PluginManager pm = Bukkit.getPluginManager();

        getCommand("tntfill").setExecutor(new Tntfill(this));
        getCommand("tf").setExecutor(new Tntfill(this));
        getCommand("discord").setExecutor(new Discord(this));
        getCommand("ce").setExecutor(new ClearEntities(this));
        getCommand("features").setExecutor(new Features(this));
        getCommand("magicsand").setExecutor(new MagicSand(this));
        getCommand("magicconc").setExecutor(new MagicConc(this));
        getCommand("getstacker").setExecutor(new Stackers(this));
        getCommand("mc").setExecutor(new MagicConc(this));
        getCommand("gs").setExecutor(new Stackers(this));
        getCommand("ms").setExecutor(new MagicSand(this));
        getCommand("fire").setExecutor(fire);
        //getCommand("et").setExecutor(tracker);
        getCommand("ct").setExecutor(tracker);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
