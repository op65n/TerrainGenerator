package op65n.tech.terraingeneration.renderer.view;

import op65n.tech.terraingeneration.renderer.ImageRenderer;
import op65n.tech.terraingeneration.renderer.view.component.ButtonComponent;
import op65n.tech.terraingeneration.terrain.TerrainGeneration;
import op65n.tech.terraingeneration.terrain.setting.TerrainSetting;
import op65n.tech.terraingeneration.terrain.setting.object.OctaveOffset;

import javax.swing.*;
import java.awt.*;
import java.util.SplittableRandom;

public final class TerrainView {

    /*
    private static final TerrainGeneration GENERATION = new TerrainGeneration(new TerrainSetting(
            800, 800, 50.0, 5, 2.0, 0.5, 0, new OctaveOffset(0F, 0F)
    ));
     */
    private static final SplittableRandom RANDOM = new SplittableRandom();
    private static final TerrainGeneration GENERATION = new TerrainGeneration(new TerrainSetting(
            RANDOM.nextInt(500, 800), RANDOM.nextInt(500, 800),
            RANDOM.nextDouble(40, 100), RANDOM.nextInt(3, 10),
            RANDOM.nextDouble(2, 3), RANDOM.nextDouble(0.4, 0.7),
            0, new OctaveOffset(0, 0)
    ));
    private final ImageRenderer renderer = new ImageRenderer(GENERATION);

    public TerrainView() {
        final JFrame frame = new JFrame("Terrain Mapping");

        frame.setBackground(Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);

        frame.add(renderer);

        frame.add(set(new ButtonComponent() {
            @Override
            public JButton adjust() {
                setPreferredSize(new Dimension(10, 5));
                setText("Generate World");

                addActionListener((event) -> {
                    renderer.render();
                });

                return this;
            }
        }));
    }

    private JButton set(final ButtonComponent component) {
        return component.defaultSettings().adjust();
    }

}
