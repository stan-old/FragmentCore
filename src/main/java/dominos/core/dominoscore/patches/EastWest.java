package dominos.core.dominoscore.patches;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.util.Vector;

import java.util.Iterator;

public class EastWest implements Listener {
    @EventHandler

    public void EntitySpawnEvent  (EntitySpawnEvent e) {

        if (e.getEntity().getType() == EntityType.PRIMED_TNT) {
            Entity entity = e.getEntity();
            if(entity.toString().equals("CraftTNTPrimed")){
                TNTPrimed tnt = (TNTPrimed) e.getEntity();
                tnt.setVelocity(new Vector(0, e.getEntity().getVelocity().getY(), 0));
                tnt.getLocation().setDirection(new Vector(0,  e.getEntity().getVelocity().getY(), 0));

            }


        }

    }
}