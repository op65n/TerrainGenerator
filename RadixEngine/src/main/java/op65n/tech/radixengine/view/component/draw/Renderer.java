package op65n.tech.radixengine.view.component.draw;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public final class Renderer {

    private static final Font DEFAULT_FONT = new Font("Tahoma", Font.PLAIN, 12);
    private final FontRenderContext context = new FontRenderContext(new AffineTransform(), true, true);
    private final BufferedImage image;

    public Renderer(final BufferedImage image) {
        this.image = image;
    }

    public void drawPixel(final int x, final int y, final Color color) {
        if (x < 0 || x >= this.image.getWidth() || y < 0 || y >= this.image.getHeight()) {
            return;
        }

        this.image.setRGB(x, y, color.getRGB());
    }

    public void drawText(final String text, final int offsetX, final int offsetY, final Color color) {
        final Rectangle2D boundary = DEFAULT_FONT.getStringBounds(text, context);

        for (int index = 0; index < text.length(); index++) {
            for (int y = 0; y < boundary.getHeight(); y++) {
                for (int x = 0; x < boundary.getWidth(); x++) {
                    drawPixel(x + offsetX, y + offsetY, color);
                }
            }
        }
    }

    public void drawRectangle(final int startX, final int offsetX, final int startY, final int offsetY, RectangleType type, final Color color) {
        if (type == null) type = RectangleType.FILLED;

        for (int x = startX; x <= offsetX + startX; x++) {
            for (int y = startY; y <= offsetY + startY; y++) {
                if (type == RectangleType.FILLED) {
                    drawPixel(x, y, color);
                    continue;
                }

                if (y == startY || y == offsetY)
                    drawPixel(x, y, color);

                if (x == startX || x == offsetX)
                    drawPixel(x, y, color);
            }
        }
    }

    public enum RectangleType {
        EMPTY, FILLED
    }

}
