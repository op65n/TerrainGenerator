package op65n.tech.terraingenerator;

import op65n.tech.terraingenerator.world.impl.PerlinGeneratorTest;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("unused")
public final class TerrainGeneratorPlugin extends JavaPlugin {

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id) {
        return new PerlinGeneratorTest();
    }
}
