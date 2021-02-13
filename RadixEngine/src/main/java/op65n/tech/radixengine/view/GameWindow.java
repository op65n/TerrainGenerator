package op65n.tech.radixengine.view;

import op65n.tech.radixengine.view.component.WindowConstraint;
import op65n.tech.radixengine.view.component.WindowProperty;
import op65n.tech.radixengine.view.component.draw.Renderer;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public final class GameWindow {

    private final JFrame frame;
    private Display display;

    private final WindowConstraint constraint;
    private final WindowProperty properties;

    public GameWindow(final String application, final WindowConstraint constraint, final WindowProperty properties) {
        this.constraint = constraint;
        this.properties = properties;

        this.frame = new JFrame(String.format("RadixEngine - %s", application));
    }

    public void start(final Consumer<GameWindow> consumer) {
        this.frame.setVisible(true);
        this.display = new Display(setDimension(constraint), constraint);
        setProperties(properties);

        consumer.accept(this);
    }

    public void update(final Consumer<GameWindow> consumer) {
        consumer.accept(this);
    }

    public void stop(final Consumer<GameWindow> consumer) {
        consumer.accept(this);
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public WindowConstraint getConstraint() {
        return this.constraint;
    }

    public Display getDisplay() {
        return this.display;
    }

    private void setProperties(final WindowProperty properties) {
        this.frame.setBackground(properties.getColor());
        this.frame.setLayout(properties.getLayoutManager());
        this.frame.setResizable(properties.isResizable());
        this.frame.setLocationRelativeTo(null);

        this.frame.setDefaultCloseOperation(properties.getConstants());

        this.frame.pack();
    }

    private Canvas setDimension(final WindowConstraint constraint) {
        final Dimension dimension = constraint.getSizeDimension();
        this.frame.setSize(dimension);
        this.frame.setPreferredSize(dimension);
        this.frame.setMaximumSize(dimension);

        final Canvas canvas = new Canvas();
        canvas.setPreferredSize(dimension);
        canvas.setMinimumSize(dimension);
        canvas.setMaximumSize(dimension);
        canvas.createBufferStrategy(1);

        this.frame.add(canvas, "Center");
        return canvas;
    }

    public static class Display {
        private final BufferStrategy bufferStrategy;
        private final Graphics graphics;
        private final BufferedImage image;
        private final Renderer renderer;

        public Display(final Canvas canvas, final WindowConstraint constraint) {
            this.bufferStrategy = canvas.getBufferStrategy();
            this.graphics = this.bufferStrategy.getDrawGraphics();
            this.image = new BufferedImage(constraint.getWidth(), constraint.getHeight(), constraint.getImageType());

            this.graphics.drawImage(image, 0, 0, constraint.getWidth(), constraint.getHeight(), null);
            this.bufferStrategy.show();

            this.renderer = new Renderer(image);
        }

        public Renderer getRenderer() {
            return this.renderer;
        }

        public BufferStrategy getBufferStrategy() {
            return this.bufferStrategy;
        }

        public Graphics getGraphics() {
            return this.graphics;
        }

        public BufferedImage getImage() {
            return this.image;
        }
    }

}
