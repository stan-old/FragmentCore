package dominos.core.dominoscore.features.customentities;

import net.minecraft.world.entity.monster.EntityShulker;
import org.bukkit.Location;
import net.minecraft.world.entity.EntityTypes;
import org.bukkit.craftbukkit.v1_17_R1.CraftWorld;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftShulker;

//thanks to bantuerfei for making this

public class CustomBlockMarker extends EntityShulker {

    public CraftShulker bukkitEntity;

    public CustomBlockMarker(Location loc) {
        super(EntityTypes.ay, ((CraftWorld) loc.getWorld()).getHandle());
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