package op65n.tech.radixengine.manager.component.asset;

import java.awt.image.BufferedImage;

public final class Image {

    private int[] pixels;
    private int width;
    private int height;

    public Image(final BufferedImage image) {
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.pixels = image.getRGB(0, 0, this.width, this.height, null, 0, this.width);
        image.flush();
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(final int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(final int height) {
        this.height = height;
    }

    public int[] getPixels() {
        return this.pixels;
    }

    public void setPixels(final int[] pixels) {
        this.pixels = pixels;
    }
}
