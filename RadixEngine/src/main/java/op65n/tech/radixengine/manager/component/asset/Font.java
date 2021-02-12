package op65n.tech.radixengine.manager.component.asset;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public final class Font {

    private Image image;
    private int[] offsets;
    private int[] widths;

    public Font(final BufferedImage image) {
        this.image = new Image(image);

        this.offsets = new int[100];
        this.widths = new int[100];

        int unicode = 0;
        for (int width = 0; width < this.image.getWidth(); width++) {
            if (this.image.getPixels()[width] == -16776961) {
                this.offsets[unicode] = width;
            }
            if (this.image.getPixels()[width] == -256) {
                this.widths[unicode] = width - this.offsets[unicode];
                unicode++;
            }
        }
    }

    public Image getImage() {
        return this.image;
    }

    public int[] getOffsets() {
        return this.offsets;
    }

    public int[] getWidths() {
        return this.widths;
    }

    public static final Font DEFAULT = new Font(getImage("font.png"));

    private static BufferedImage getImage(final String path) {
        try {
            final InputStream stream = Font.class.getResourceAsStream("resources/" + path);
            if (stream == null)
                return new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);

            return ImageIO.read(stream);
        } catch (final IOException exception) {
            return new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        }
    }

}
