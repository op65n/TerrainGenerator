package op65n.tech.radixengine.view.component;

import javax.swing.*;
import java.awt.*;

public final class WindowProperty {

    private final Color color;
    private final LayoutManager2 layoutManager;
    private final int constants;
    private final boolean resizable;

    private WindowProperty(final Color color, final LayoutManager2 layout, final int constants, final boolean resizable) {
        this.color = color;
        this.layoutManager = layout;
        this.constants = constants;
        this.resizable = resizable;
    }

    public Color getColor() {
        return this.color;
    }

    public LayoutManager2 getLayoutManager() {
        return this.layoutManager;
    }

    public int getConstants() {
        return this.constants;
    }

    public boolean isResizable() {
        return this.resizable;
    }

    public static class WindowPropertyBuilder {

        private Color background = Color.BLACK;
        private LayoutManager2 layoutManager = new BorderLayout();
        private int constants = WindowConstants.EXIT_ON_CLOSE;
        private boolean resizable = false;

        public WindowPropertyBuilder withBackground(final Color background) {
            this.background = background;
            return this;
        }

        public WindowPropertyBuilder withLayout(final LayoutManager2 layout) {
            this.layoutManager = layout;
            return this;
        }

        public WindowPropertyBuilder withConstants(final int constants) {
            this.constants = constants;
            return this;
        }

        public WindowPropertyBuilder isResizable(final boolean resizable) {
            this.resizable = resizable;
            return this;
        }

        public WindowProperty build() {
            return new WindowProperty(this.background, this.layoutManager, this.constants, this.resizable);
        }

    }

}
