package am2.spell.shapes;

import am2.api.spell.ItemSpellBase;
import am2.api.spell.component.interfaces.ISpellShape;
import am2.api.spell.enums.Affinity;
import am2.api.spell.enums.SpellCastResult;
import am2.items.ItemsCommonProxy;
import am2.playerextensions.BoundPlayer;
import am2.spell.SpellHelper;
import am2.spell.SpellUtils;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;

public class Remote implements ISpellShape{

	@Override
	public int getID(){
		return 20;
	}

	@Override
	public SpellCastResult beginStackStage(ItemSpellBase item, ItemStack stack, EntityLivingBase caster, EntityLivingBase target, World world, double x, double y, double z, int side, boolean giveXP, int useCount){
		String targetPlayerName = (String)(BoundPlayer.For(caster).getData())[0];
		if (targetPlayerName == null) return null;
		EntityPlayer targetPlayer = MinecraftServer.getServer().getConfigurationManager().func_152612_a(targetPlayerName);
		if (targetPlayer == null) return null;

		SpellCastResult result = SpellHelper.instance.applyStageToEntity(stack, caster, world, targetPlayer, 0, giveXP);
		if (result != SpellCastResult.SUCCESS){
			return result;
		}

		ItemStack newItemStack = SpellUtils.instance.popStackStage(stack);
		return SpellHelper.instance.applyStackStage(newItemStack, caster, target, x, y, z, 0, world, true, giveXP, 0);
	}

	@Override
	public boolean isChanneled(){
		return false;
	}

	@Override
	public Object[] getRecipeItems(){
		return new Object[]{
				Items.compass,
				Items.ender_eye,
				Items.arrow,
				Items.golden_sword,
				new ItemStack(ItemsCommonProxy.itemOre, 1, ItemsCommonProxy.itemOre.META_VINTEUMDUST)
		};
	}

	@Override
	public float manaCostMultiplier(ItemStack spellStack){
		return 0.5f;
	}

	@Override
	public boolean isTerminusShape(){
		return false;
	}

	@Override
	public boolean isPrincipumShape(){
		return false;
	}

	@Override
	public String getSoundForAffinity(Affinity affinity, ItemStack stack, World world){
		switch (affinity){
		case AIR:
			return "arsmagica2:spell.cast.air";
		case ARCANE:
			return "arsmagica2:spell.cast.arcane";
		case EARTH:
			return "arsmagica2:spell.cast.earth";
		case ENDER:
			return "arsmagica2:spell.cast.ender";
		case FIRE:
			return "arsmagica2:spell.cast.fire";
		case ICE:
			return "arsmagica2:spell.cast.ice";
		case LIFE:
			return "arsmagica2:spell.cast.life";
		case LIGHTNING:
			return "arsmagica2:spell.cast.lightning";
		case NATURE:
			return "arsmagica2:spell.cast.nature";
		case WATER:
			return "arsmagica2:spell.cast.water";
		case NONE:
		default:
			return "arsmagica2:spell.cast.none";
		}
	}
}
