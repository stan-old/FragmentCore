package dominos.core.dominoscore.features.customentities;

import net.minecraft.server.v1_16_R3.EntityArmorStand;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftArmorStand;
import org.bukkit.inventory.ItemStack;

//thanks to bantuerfei for making this

public class CustomExplosionMarker extends EntityArmorStand {

    public CraftArmorStand bukkitEntity;

    public CustomExplosionMarker(Location loc) {
        super(((CraftWorld) loc.getWorld()).getHandle(), loc.getX(), loc.getY(), loc.getZ());
        this.setPosition(loc.getX(), loc.getY() - this.getHeadHeight()/2 + 0.49, loc.getZ());
        this.bukkitEntity = (CraftArmorStand) this.getBukkitEntity();
        this.bukkitEntity.setInvisible(true);
        this.bukkitEntity.setMarker(true);
        this.bukkitEntity.setSmall(true);
        this.bukkitEntity.setRotation(0, 0);
        this.bukkitEntity.setHelmet(new ItemStack(Material.RED_WOOL, 1));
    }

    public void remove() {
        this.bukkitEntity.remove();
    }

}
