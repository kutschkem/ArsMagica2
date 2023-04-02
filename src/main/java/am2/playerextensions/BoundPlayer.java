package am2.playerextensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class BoundPlayer implements IExtendedEntityProperties {
	public static final String identifier = "ArsMagicaBoundPlayer";
	
	public String name;

	public static BoundPlayer For(EntityLivingBase living){
		return (BoundPlayer)living.getExtendedProperties(identifier);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound){
		compound.setString("boundPlayer", name);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound){
		this.name =  compound.getString("boundPlayer"); 
	}

	@Override
	public void init(Entity entity, World world){
		this.name = null;
	}
	
	public void setBoundPlayer(String name) {
		this.name = name;
	}
	
	public String getBoundPlayer() {
		return this.name;
	}
}
