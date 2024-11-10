package org.zeith.modid.scripting.actions;

import com.google.gson.JsonObject;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;

import java.util.logging.Logger;

public class MobBehaviorAction {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void execute(JsonObject action, Mob mob, ServerLevel world) {
        boolean makeAggressive = action.get("aggressive").getAsBoolean();

        if (makeAggressive) {
            mob.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(mob, Player.class, true));
            LOGGER.info("Mob set to aggressive mode.");
        }
    }
}