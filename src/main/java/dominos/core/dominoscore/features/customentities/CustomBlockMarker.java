package dominos.core.dominoscore.features.customentities;

import net.minecraft.server.v1_16_R3.EntityShulker;
import org.bukkit.Location;
import net.minecraft.server.v1_16_R3.EntityTypes;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftShulker;

//thanks to bantuerfei for making this

public class CustomBlockMarker extends EntityShulker {

    public CraftShulker bukkitEntity;

    public CustomBlockMarker(Location loc) {
        super(EntityTypes.SHULKER, ((CraftWorld) loc.getWorld()).getHandle());
        this.setLocation(loc.getX(), loc.getY(), loc.getZ(), 0, 0);
        this.bukkitEntity = (CraftShulker) this.getBukkitEntity();
        this.bukkitEntity.setInvisible(true);
        this.bukkitEntity.setGlowing(true);
        this.bukkitEntity.setAI(false);
        this.bukkitEntity.setSilent(true);
        this.bukkitEntity.setInvulnerable(true);
        this.bukkitEntity.setRotation(0, 0);
    }

    public void remove(){
        this.bukkitEntity.remove();
    }
}