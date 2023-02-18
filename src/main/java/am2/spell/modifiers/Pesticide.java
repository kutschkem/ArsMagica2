package am2.spell.modifiers;

import java.util.EnumSet;

import am2.api.spell.component.interfaces.ISpellModifier;
import am2.api.spell.enums.SpellModifiers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class Pesticide implements ISpellModifier{
	@Override
	public EnumSet<SpellModifiers> getAspectsModified(){
		return EnumSet.of(SpellModifiers.SMITE);
	}

	@Override
	public float getModifier(SpellModifiers type, EntityLivingBase caster, Entity target, World world, byte[] metadata){
		if (target instanceof EntityCreature) if (target instanceof EntitySpider || ((EntityCreature)target).getCreatureAttribute() == EnumCreatureAttribute.UNDEAD) return 1;
		return 0;
	}

	@Override
	public int getID(){
		return 551;
	}

	@Override
	public Object[] getRecipeItems(){
		return new Object[]{
				Items.spider_eye,
				Items.iron_sword,
				Items.rotten_flesh
		};
	}

	@Override
	public float getManaCostMultiplier(ItemStack spellStack, int stage, int quantity){
		return 1.2f * quantity;
	}

	@Override
	public byte[] getModifierMetadata(ItemStack[] matchedRecipe){
		return null;
	}
}
