package op65n.tech.radixengine.manager.component.constraint;

import java.awt.*;

public final class WindowConstraint {

    private final Canvas canvas = new Canvas();
    private final int width;
    private final int height;
    private final float scale;

    public WindowConstraint(final int width, final int height, final float scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
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

    public static class ConstraintBuilder {
        private int width;
        private int height;
        private float scale;

        public ConstraintBuilder withWidth(final int width) {
            this.width = width;
            return this;
        }

        public ConstraintBuilder withHeight(final int height) {
            this.height = height;
            return this;
        }

        public ConstraintBuilder withScale(final float scale) {
            this.scale = scale;
            return this;
        }

        public WindowConstraint build() {
            return new WindowConstraint(width, height, scale);
        }
    }

}
