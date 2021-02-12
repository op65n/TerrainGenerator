package op65n.tech.terraingeneration.renderer.view;

import op65n.tech.radixengine.component.Container;
import op65n.tech.radixengine.manager.GameManager;
import op65n.tech.terraingeneration.renderer.ImageRenderer;
import op65n.tech.terraingeneration.terrain.TerrainGeneration;
import op65n.tech.terraingeneration.terrain.setting.TerrainSetting;
import op65n.tech.terraingeneration.terrain.setting.object.OctaveOffset;

import java.awt.*;
import java.util.SplittableRandom;

public final class TerrainView {

    private static final SplittableRandom RANDOM = new SplittableRandom();
    private static final TerrainGeneration GENERATION = new TerrainGeneration(new TerrainSetting(
            400, 300,
            RANDOM.nextDouble(40, 100), RANDOM.nextInt(3, 10),
            RANDOM.nextDouble(2, 3), RANDOM.nextDouble(0.4, 0.7),
            0, new OctaveOffset(0, 0)
    ));

    private final ImageRenderer renderer = new ImageRenderer(GENERATION);

    public TerrainView() {
        final GameManager manager = new GameManager();
        final Container container = new Container(manager, "Terrain Mapping");
        container.assignProperties(400, 300, 2.0F);

        manager.setDrawable(() -> {
            renderer.render(container);
            System.out.println("Called Render method");
        });
        container.getRenderer().drawText("THIS IS A TEST", 0, 10, Color.WHITE);

        container.start();
    }

}
