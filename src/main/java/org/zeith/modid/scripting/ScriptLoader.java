package org.zeith.modid.scripting;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Mob;

import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

public class ScriptLoader {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");
    private static final Gson GSON = new Gson();

    public static void loadAndRunScripts(String filePath, ServerLevel world, ServerPlayer player, Mob mob) {
        try (FileReader reader = new FileReader(filePath)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            JsonArray actions = json.getAsJsonArray("actions");

            for (JsonElement actionElem : actions) {
                JsonObject action = actionElem.getAsJsonObject();
                String actionType = action.get("type").getAsString();

                switch (actionType) {
                    case "message", "set_weather", "set_time", "animation" ->
                            ActionProcessor.processAction(actionType, action, world, player, null);

                    case "mob_behavior" -> {
                        if (mob != null)
                            ActionProcessor.processAction(actionType, action, world, player, mob);
                        else
                            LOGGER.warning("Action 'mob_behavior' specified but no mob provided.");
                    }

                    case "spawn_entity" ->
                            ActionProcessor.processAction(actionType, action, world, player, null);

                    default ->
                            LOGGER.warning("Unknown action type: " + actionType);
                }
            }
        }
        catch (IOException e) {
            LOGGER.severe("Failed to load script file: " + e.getMessage());
        }
    }
}