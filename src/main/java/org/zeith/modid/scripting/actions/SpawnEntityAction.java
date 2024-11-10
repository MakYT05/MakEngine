package org.zeith.modid.scripting.actions;

import com.google.gson.JsonObject;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;

import java.util.logging.Logger;

public class SpawnEntityAction {
    private static final Logger LOGGER = Logger.getLogger("MakEngine");

    public static void execute(JsonObject action, ServerLevel world, Player player) {
        String entityId = action.get("entity_id").getAsString();
        int x = action.get("x").getAsInt();
        int y = action.get("y").getAsInt();
        int z = action.get("z").getAsInt();

        EntityType<?> entityType = EntityType.byString(entityId).orElse(null);

        if (entityType != null && entityType.create(world) instanceof Mob mob) {
            mob.setPos(x, y, z);
            world.addFreshEntity(mob);
            LOGGER.info("Spawned entity: " + entityId + " at " + x + ", " + y + ", " + z);
        }
        else {
            LOGGER.warning("Failed to spawn entity: " + entityId);
        }
    }
}