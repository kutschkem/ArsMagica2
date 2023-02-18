package am2.spell.components;

import java.util.EnumSet;
import java.util.Random;

import am2.AMCore;
import am2.api.ArsMagicaApi;
import am2.api.spell.component.interfaces.ISpellComponent;
import am2.api.spell.enums.Affinity;
import am2.playerextensions.BoundPlayer;
import am2.spell.SpellUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class Locate implements ISpellComponent {
	@Override
	public boolean applyEffectBlock(ItemStack stack, World world, int blockx, int blocky, int blockz, int blockFace, double impactX, double impactY, double impactZ, EntityLivingBase caster){
		return applyEffect(stack, caster);
	}

	@Override
	public boolean applyEffectEntity(ItemStack stack, World world, EntityLivingBase caster, Entity target) {
		return applyEffect(stack, caster);
	}
	
	public boolean applyEffect(ItemStack stack, EntityLivingBase caster) {
		String searcherName = SpellUtils.instance.getSpellMetadata(stack, "Searcher");
		EntityPlayer searcher = MinecraftServer.getServer().getConfigurationManager().func_152612_a(searcherName);
		if (searcher == null) return false;
		
		double posX = caster.posX;
		double posY = caster.posY;
		double posZ = caster.posZ;
		int dimension = caster.dimension;
		String casterName = null;
		if (caster instanceof EntityPlayer) casterName = ((EntityPlayer)caster).getDisplayName();
		
		BoundPlayer boundPlayerData = BoundPlayer.For(searcher);
		boundPlayerData.setData(casterName, dimension, posX, posY, posZ);
		
		return true;
	}

	@Override
	public float manaCost(EntityLivingBase caster){
		return 500;
	}

	@Override
	public float burnout(EntityLivingBase caster){
		return ArsMagicaApi.getBurnoutFromMana(manaCost(caster));
	}

	@Override
	public ItemStack[] reagents(EntityLivingBase caster){
		return null;
	}

	@Override
	public void spawnParticles(World world, double x, double y, double z, EntityLivingBase caster, Entity target, Random rand, int colorModifier){
	}

	@Override
	public EnumSet<Affinity> getAffinity(){
		return EnumSet.of(Affinity.NONE);
	}

	@Override
	public int getID(){
		return 552;
	}

	@Override
	public Object[] getRecipeItems(){
		return new Object[]{
				Items.compass,
				Items.ender_eye,
				Items.map,
				Items.name_tag
		};
	}

	@Override
	public float getAffinityShift(Affinity affinity){
		return 0.0f;
	}
	
	public void setSearcher(ItemStack stack, ItemStack nametagStack){
		SpellUtils.instance.setSpellMetadata(stack, "Searcher", nametagStack.getDisplayName());
	}
}
