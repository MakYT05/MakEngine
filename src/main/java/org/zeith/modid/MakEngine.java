package org.zeith.modid;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import org.zeith.hammerlib.api.items.CreativeTab;
import org.zeith.hammerlib.core.adapter.LanguageAdapter;
import org.zeith.hammerlib.core.init.ItemsHL;
import org.zeith.hammerlib.proxy.HLConstants;
import org.zeith.modid.scripting.ScriptLoader;

@Mod(MakEngine.MOD_ID)
public class MakEngine {
	public static final String MOD_ID = "modid";

	@CreativeTab.RegisterTab
	public static final CreativeTab MOD_TAB = new CreativeTab(id("mak"),
			builder -> builder
					.icon(() -> ItemsHL.COPPER_GEAR.getDefaultInstance())
					.withTabsBefore(HLConstants.HL_TAB.id())
	);

	public MakEngine(Level world, ServerPlayer player, Mob mob) {
		LanguageAdapter.registerMod(MOD_ID);
		ScriptLoader.loadAndRunScripts("scripts.json", (ServerLevel) world, player, mob);
	}

	public static ResourceLocation id(String path) { return new ResourceLocation(MOD_ID, path); }
}