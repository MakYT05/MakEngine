package org.zeith.modid.scripting.actions;

import com.google.gson.JsonObject;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;

import java.util.logging.Logger;

public class AnimationAction {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void execute(JsonObject action, ServerLevel world) {
        String particle = action.get("particle").getAsString();
        int x = action.get("x").getAsInt();
        int y = action.get("y").getAsInt();
        int z = action.get("z").getAsInt();
        int count = action.get("count").getAsInt();

        ParticleOptions particleType = switch (particle) {
            case "smoke" -> ParticleTypes.SMOKE;
            case "flame" -> ParticleTypes.FLAME;
            case "cloud" -> ParticleTypes.CLOUD;
            default -> null;
        };

        if (particleType != null) {
            world.sendParticles(particleType, x, y, z, count, 0.5, 0.5, 0.5, 0.1);
            LOGGER.info("Displayed animation at: " + x + ", " + y + ", " + z);
        } else {
            LOGGER.warning("Unknown particle type: " + particle);
        }
    }
}