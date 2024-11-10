package org.zeith.modid.scripting.actions;

import com.google.gson.JsonObject;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

import java.util.logging.Logger;

public class MessageAction {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void execute(JsonObject action, ServerPlayer player) {
        String message = action.get("text").getAsString();
        player.sendSystemMessage(Component.literal(message));
        LOGGER.info("Message sent to player: " + message);
    }
}