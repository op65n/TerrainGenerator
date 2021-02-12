package op65n.tech.radixengine.manager.component;

import op65n.tech.radixengine.manager.component.asset.Font;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Renderer {

    private final int pixelWidth;
    private final int pixelHeight;
    private final BufferedImage image;

    private final Font font = Font.DEFAULT;

    public Renderer(final Container container) {
        this.pixelWidth = container.getWidth();
        this.pixelHeight = container.getHeight();
        this.image = container.getWindow().getImage();
    }

    public void clear() {
        for (int y = 0; y < this.pixelHeight; y++) {
            for (int x = 0; x < this.pixelWidth; x++) {
                this.image.setRGB(x, y, 0);
            }
        }
    }

    public void drawText(final String text, final int offsetX, final int offsetY, final Color color) {
        int offset = 0;
        for (int letter = 0; letter < text.length(); letter++) {
            final int unicode = text.codePointAt(letter) - 32;
            for (int y = 0; y < this.font.getImage().getHeight(); y++) {
                for (int x = 0; x < this.font.getImage().getWidth(); x++) {
                    drawPixel(x + offsetX + offset, y + offsetY, color);
                }
            }
            offset += this.font.getWidths()[unicode];
        }
    }

    public void drawPixel(final int x, final int y, final Color color) {
        if (x < 0 || x >= this.pixelWidth || y < 0 || y >= this.pixelHeight) return;

        this.image.setRGB(x, y, color.getRGB());
    }

}
