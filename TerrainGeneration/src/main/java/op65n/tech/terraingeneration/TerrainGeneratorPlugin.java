package op65n.tech.terraingeneration;

import op65n.tech.terraingeneration.world.impl.PerlinGenerator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class TerrainGeneratorPlugin extends JavaPlugin {

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull final String worldName, @Nullable final String id) {
        return new PerlinGenerator();
    }

}
