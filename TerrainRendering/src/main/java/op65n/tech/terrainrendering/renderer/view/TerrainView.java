package op65n.tech.terrainrendering.renderer.view;

import op65n.tech.terrainrenderer.terrain.TerrainGeneration;
import op65n.tech.terrainrenderer.terrain.setting.TerrainSetting;
import op65n.tech.terrainrenderer.terrain.setting.object.OctaveOffset;

import java.util.SplittableRandom;

public final class TerrainView {

    private static final SplittableRandom RANDOM = new SplittableRandom();
    private static final TerrainGeneration GENERATION = new TerrainGeneration(new TerrainSetting(
            400, 300,
            RANDOM.nextDouble(40, 100), RANDOM.nextInt(3, 10),
            RANDOM.nextDouble(2, 3), RANDOM.nextDouble(0.4, 0.7),
            0, new OctaveOffset(0, 0)
    ));

    public TerrainView() {

    }

}
