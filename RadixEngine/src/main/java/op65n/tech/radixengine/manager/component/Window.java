package op65n.tech.radixengine.manager.component;

import op65n.tech.radixengine.manager.component.constraint.WindowConstraint;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public final class Window {

    private final BufferedImage image;

    public Window(final Container container) {
        final WindowConstraint constraint = container.getConstraint();


        this.image = new BufferedImage(constraint.getWidth(), constraint.getHeight(), BufferedImage.TYPE_INT_RGB);

        final Dimension dimension = new Dimension((int) (constraint.getWidth() * constraint.getScale()), (int) (container.getWidth() * container.getScale()));

    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

}
