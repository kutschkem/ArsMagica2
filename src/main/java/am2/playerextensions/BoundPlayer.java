package am2.playerextensions;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class BoundPlayer implements IExtendedEntityProperties {
	public static final String identifier = "ArsMagicaBoundPlayer";
	
	public String boundPlayerName;
	public int dimension;
	public double posX;
	public double posY;
	public double posZ;

	public static BoundPlayer For(EntityLivingBase living){
		return (BoundPlayer)living.getExtendedProperties(identifier);
	}

	@Override
	public void saveNBTData(NBTTagCompound compound){
		NBTTagCompound boundPlayerTags = new NBTTagCompound();
		boundPlayerTags.setString("name", boundPlayerName);
		boundPlayerTags.setInteger("dimension", dimension);
		boundPlayerTags.setDouble("posX", posX);
		boundPlayerTags.setDouble("posY", posY);
		boundPlayerTags.setDouble("posZ", posZ);
		compound.setTag("boundPlayer", boundPlayerTags);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound){
		NBTTagCompound boundPlayerTags = compound.getCompoundTag("boundPlayer");
		this.boundPlayerName =  boundPlayerTags.getString("name"); 
		this.dimension = boundPlayerTags.getInteger("dimension");
		this.posX = boundPlayerTags.getDouble("posX");
		this.posY = boundPlayerTags.getDouble("posY");
		this.posZ = boundPlayerTags.getDouble("posZ");
	}

	@Override
	public void init(Entity entity, World world){
		this.posX = 0;
		this.posY = 0;
		this.posZ = 0;
		this.dimension = 0;
		this.boundPlayerName = null;
	}
	
	public void setData(String boundPlayerName, int dimension, double posX, double posY, double posZ) {
		this.boundPlayerName = boundPlayerName;
		this.dimension = dimension;
		this.posX = posX;
		this.posY = posY;
		this.posZ = posZ;
	}
	
	public Object[] getData() {
		Object[] data = {this.boundPlayerName, this.dimension, this.posX, this.posY, this.posZ};
		return data;
	}
}
