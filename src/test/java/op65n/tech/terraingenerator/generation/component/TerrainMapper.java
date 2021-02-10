package op65n.tech.terraingenerator.generation.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public final class TerrainMapper extends JPanel {

    public TerrainMapper(final float[][] noiseMap) {
        final BufferedImage image = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
        final ImageIcon icon = new ImageIcon(image);
        add(new JLabel(icon));

        for (int z = 100; z < noiseMap.length; z++) {
            for (int x = 0; x < noiseMap[1].length; x++) {
                float value;
                try {
                    value = noiseMap[x][z];
                } catch (final ArrayIndexOutOfBoundsException ignored) {
                    continue;
                }

                final ImageMapper mapper = ImageMapper.getValue(value);
                final Color color = new Color(mapper.getR(), mapper.getG(), mapper.getB());

                image.setRGB(x, z, color.getRGB());
            }
        }
    }

}
