package am2.spell.components;

import java.util.EnumSet;
import java.util.Random;

import am2.api.ArsMagicaApi;
import am2.api.spell.component.interfaces.ISpellComponent;
import am2.api.spell.enums.Affinity;
import am2.playerextensions.BoundPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;

public class Search implements ISpellComponent {

	@Override
	public boolean applyEffectBlock(ItemStack stack, World world, int blockx, int blocky, int blockz, int blockFace, double impactX, double impactY, double impactZ, EntityLivingBase caster){
		return applyEffect(stack, caster);
	}

	@Override
	public boolean applyEffectEntity(ItemStack stack, World world, EntityLivingBase caster, Entity target){
		return applyEffect(stack, caster);
	}
	
	public boolean applyEffect(ItemStack stack, EntityLivingBase caster) {
		EntityPlayer boundPlayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(BoundPlayer.For(caster).getBoundPlayer());
				
		if (caster instanceof EntityPlayer && (boundPlayer != null)) {
				((EntityPlayer)caster).addChatMessage(new ChatComponentText("Name: " + boundPlayer.getDisplayName()));
				((EntityPlayer)caster).addChatMessage(new ChatComponentText("Dimension: " + boundPlayer.dimension));
				return true;
		}
		return false;
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
		return 553;
	}

	@Override
	public Object[] getRecipeItems(){
		return new Object[]{
				Items.clock,
				Items.map,
				Items.compass,
				Items.ender_eye,
				Items.arrow
		};
	}

	@Override
	public float getAffinityShift(Affinity affinity){
		return 0.0f;
	}
}
