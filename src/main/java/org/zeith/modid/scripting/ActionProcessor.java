package org.zeith.modid.scripting;

import com.google.gson.JsonObject;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;
import org.zeith.modid.scripting.actions.*;

import java.util.logging.Logger;

public class ActionProcessor {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void processAction(String actionType, JsonObject action, ServerLevel world, ServerPlayer player, Mob mob) {
        switch (actionType) {
            case "message" -> MessageAction.execute(action, player);
            case "spawn_entity" -> SpawnEntityAction.execute(action, world, player);
            case "set_weather" -> WeatherAction.execute(action, world);
            case "set_time" -> TimeAction.execute(action, world);
            case "animation" -> AnimationAction.execute(action, world);
            case "mob_behavior" -> {
                if (mob != null) {
                    MobBehaviorAction.execute(action, mob, world);
                }
            }
            default -> LOGGER.warning("Unknown action type: " + actionType);
        }
    }
}