package op65n.tech.radixengine.view.component;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class WindowConstraint {

    private final int width;
    private final int height;
    private final float scale;
    private final int imageType;

    private WindowConstraint(final int width, final int height, final float scale, final int type) {
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.imageType = type;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public float getScale() {
        return this.scale;
    }

    public int getImageType() {
        return this.imageType;
    }

    public Dimension getSizeDimension() {
        return new Dimension(this.width, this.height);
    }

    public static class WindowBuilder {

        private int width = 900;
        private int height = 500;
        private float scale = 2.0F;
        private int imageType = BufferedImage.TYPE_INT_RGB;

        public WindowBuilder withWidth(final int width) {
            this.width = width;
            return this;
        }

        public WindowBuilder withHeight(final int height) {
            this.height = height;
            return this;
        }

        public WindowBuilder withScale(final float scale) {
            this.scale = scale;
            return this;
        }

        public WindowBuilder withType(final int type) {
            this.imageType = type;
            return this;
        }

        public WindowConstraint build() {
            return new WindowConstraint(this.width, this.height, this.scale, this.imageType);
        }

    }

}
