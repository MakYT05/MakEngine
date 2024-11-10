package org.zeith.modid.scripting.actions;

import com.google.gson.JsonObject;
import net.minecraft.server.level.ServerLevel;

import java.util.logging.Logger;

public class WeatherAction {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void execute(JsonObject action, ServerLevel world) {
        String weatherType = action.get("type").getAsString();

        switch (weatherType) {
            case "clear" -> {
                world.setWeatherParameters(12000, 0, false, false);
                LOGGER.info("Weather set to clear.");
            }
            case "rain" -> {
                world.setWeatherParameters(0, 12000, true, false);
                LOGGER.info("Weather set to rain.");
            }
            case "thunder" -> {
                world.setWeatherParameters(0, 12000, true, true);
                LOGGER.info("Weather set to thunderstorm.");
            }
            default -> LOGGER.warning("Unknown weather type: " + weatherType);
        }
    }
}