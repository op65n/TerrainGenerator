package op65n.tech.terraingeneration.renderer;

import op65n.tech.terraingeneration.renderer.map.ImageMapper;
import op65n.tech.terraingeneration.terrain.TerrainGeneration;

import javax.swing.*;
import java.awt.image.BufferedImage;

public final class ImageRenderer extends JPanel {

    private final TerrainGeneration generation;
    private BufferedImage image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    private final ImageIcon icon = new ImageIcon(image);

    public ImageRenderer(final TerrainGeneration generation) {
        this.generation = generation;
        this.add(new JLabel(icon));
        render();
    }

    public void render() {
        final float[][] noiseMap = generation.getNoiseMap();
        image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

        for (int z = 100; z < noiseMap.length; z++) {
            for (int x = 0; x < noiseMap[1].length; x++) {
                final float value;
                try { value = noiseMap[x][z];
                } catch (final ArrayIndexOutOfBoundsException ignored) { continue; }

                image.setRGB(x, z, ImageMapper.getColorForHeight(value).getRGB());
            }
        }
    }

}
