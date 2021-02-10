package op65n.tech.terraingenerator.generation;

import op65n.tech.terraingenerator.generation.button.ButtonComponent;
import op65n.tech.terraingenerator.generation.component.TerrainMapper;
import op65n.tech.terraingenerator.terrain.generation.ProceduralGeneration;
import op65n.tech.terraingenerator.terrain.generation.object.OctaveOffset;
import op65n.tech.terraingenerator.terrain.setting.TerrainSetting;

import javax.swing.*;
import java.awt.*;

public final class PerlinGeneratorTest {

    // World Settings
    private static final ProceduralGeneration GENERATION = new ProceduralGeneration(new TerrainSetting(
            1000, 1000, 50, 5, 2, 0.5, 0, new OctaveOffset(0, 0))
    );

    // Window Settings
    private final JFrame frame;
    private final JPanel panel;

    public PerlinGeneratorTest() {
        this.frame = setFrame();
        this.panel = setPanel();

    }

    private JFrame setFrame() {
        final JFrame frame = new JFrame("Terrain Mapping");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);

        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    private JPanel setPanel() {
        final JPanel panel = new TerrainMapper(GENERATION.getNoiseMap());
        panel.setBackground(Color.BLACK);
        panel.setVisible(true);

        // world
        panel.add(new ButtonComponent() {
            @Override
            public JButton handle() {
                setText("World Width");
                setToolTipText("Set the width of the generated world.");

                return this;
            }
        });
        panel.add(new ButtonComponent() {
            @Override
            public JButton handle() {
                setText("World Height");
                setToolTipText("Set the height of the generated world.");

                return this;
            }
        });

        // noise
        panel.add(new JButton("Noise Scale"));
        panel.add(new JButton("Octaves"));
        panel.add(new JButton("Lacunarity"));
        panel.add(new JButton("Persistance"));
        panel.add(new JButton("Seed"));
        panel.add(new JButton("Octave Offset"));

        frame.add(panel);
        return panel;
    }

    public ProceduralGeneration getGeneration() {
        return GENERATION;
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public JPanel getPanel() {
        return this.panel;
    }
}
