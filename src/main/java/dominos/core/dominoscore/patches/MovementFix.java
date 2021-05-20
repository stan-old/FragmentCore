package dominos.core.dominoscore.patches;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import org.bukkit.util.Vector;

public class MovementFix implements Listener {
    //TNT Damage
    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = ((Player) event.getEntity()).getPlayer();
            if (event.getCause() == EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) {
                event.setDamage(0);
                event.setCancelled(true);
            }
        }
    }
}