package org.zeith.modid.scripting.actions;

import com.google.gson.JsonObject;
import net.minecraft.server.level.ServerLevel;

import java.util.logging.Logger;

public class TimeAction {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void execute(JsonObject action, ServerLevel world) {
        int timeOfDay = action.get("time").getAsInt();
        world.setDayTime(timeOfDay);
        LOGGER.info("Time set to: " + timeOfDay);
    }
}