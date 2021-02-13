package op65n.tech.terraingeneration.terrain.populator;

import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.jetbrains.annotations.NotNull;

public abstract class TerrainPopulator extends BlockPopulator {

    public int getHighestBlockAt(@NotNull final World world, final int x, final int z) {
        return world.getHighestBlockAt(x, z).getY();
    }

}
