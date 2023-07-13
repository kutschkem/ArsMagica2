package am2.configuration;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.HashMap;


public class SkillConfiguration extends Configuration{
	private final HashMap<String, Boolean> cache;

	private static final String CATEGORY_DIMENSION = "dimensions";

	private static final String KEY_intervention_dim_bl = "intervention_dim_bl";
	private static final String KEY_rift_dim_bl = "rift_dim_bl";

	private int[] intervention_dim_bl;
	private int[] rift_dim_bl;

	public SkillConfiguration(File file){
		super(file);
		cache = new HashMap<String, Boolean>();
		load();
		addCustomCategoryComment(CATEGORY_GENERAL, "Set any of these to false to disable the skill in-game.");
		addCustomCategoryComment(CATEGORY_DIMENSION, "dimension-specific spell settings");
	}

	public boolean isSkillEnabled(String identifier){
		if (cache.containsKey(identifier))
			return cache.get(identifier);
		boolean value = get(CATEGORY_GENERAL, identifier, true).getBoolean(true);
		cache.put(identifier, value);
		return value;
	}

	public void init(){
		intervention_dim_bl = get(CATEGORY_DIMENSION, KEY_intervention_dim_bl, new int[]{1}, "Dimensions from which teleportation by means of Divine intervention etc. should not be possible (default: the End)").getIntList();
		rift_dim_bl = get(CATEGORY_DIMENSION, KEY_rift_dim_bl, new int[]{}, "Dimensions in which Rift storage is unaccessable").getIntList();
	}

	public int[] getInterventionDimBl() {
		return intervention_dim_bl;
	}

	public int[] getRiftDimBl() {
		return rift_dim_bl;
	}
}
