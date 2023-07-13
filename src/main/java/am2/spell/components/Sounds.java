package am2.spell.components;

import java.util.EnumSet;
import java.util.Random;

import am2.api.spell.component.interfaces.ISpellComponent;
import am2.api.spell.enums.Affinity;
import am2.spell.SpellUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Sounds implements ISpellComponent {
	@Override
	public boolean applyEffectBlock(ItemStack stack, World world, int blockx, int blocky, int blockz, int blockFace, double impactX, double impactY, double impactZ, EntityLivingBase caster){
		return applyEffect(stack, world, blockx, blocky, blockz);
	}

	@Override
	public boolean applyEffectEntity(ItemStack stack, World world, EntityLivingBase caster, Entity target) {
		return applyEffect(stack, world, target.posX, target.posY, target.posZ);
	}

	public boolean applyEffect(ItemStack stack, World world, double x, double y, double z) {
		String sound = SpellUtils.instance.getSpellMetadata(stack, "Sound");
		if (sound.trim().isEmpty()) sound = "botania:doit";
		world.playSoundEffect(x, y, z, sound, 0.9F, world.rand.nextFloat() - world.rand.nextFloat() * 0.2F + 1.0F);
		return true;
	}

	@Override
	public float manaCost(EntityLivingBase caster){
		return 0;
	}

	@Override
	public float burnout(EntityLivingBase caster){
		return 0;
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
		return 555;
	}

	/*
	 * Rename the noteblock to the sound that will be played on the target, e.g. 'botania:doit'
	 */
	@Override
	public Object[] getRecipeItems(){
		return new Object[]{
				Blocks.noteblock,
				Items.redstone
		};
	}

	@Override
	public float getAffinityShift(Affinity affinity){
		return 0.0f;
	}

	public void setSound(ItemStack stack, ItemStack noteblockStack){
		SpellUtils.instance.setSpellMetadata(stack, "Sound", noteblockStack.getDisplayName());
	}
}
