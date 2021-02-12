package op65n.tech.radixengine.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public final class Window {

    private final JFrame frame;
    private final BufferedImage image;
    private final Canvas canvas;
    private final BufferStrategy bufferStrategy;
    private final Graphics graphics;

    public Window(final Container container) {
        this.image = new BufferedImage(container.getWidth(), container.getHeight(), BufferedImage.TYPE_INT_RGB);
        this.canvas = new Canvas();

        final Dimension dimension = new Dimension((int) (container.getWidth() * container.getScale()), (int) (container.getWidth() * container.getScale()));

        this.canvas.setPreferredSize(dimension);
        this.canvas.setMaximumSize(dimension);
        this.canvas.setMinimumSize(dimension);

        this.frame = new JFrame("RadixEngine - " + container.getApplication());
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setLayout(new BorderLayout());
        this.frame.add(this.canvas, "Center");
        this.frame.pack();
        this.frame.setLocationRelativeTo(null);
        this.frame.setResizable(false);
        this.frame.setVisible(true);

        this.canvas.createBufferStrategy(1);
        this.bufferStrategy = this.canvas.getBufferStrategy();
        this.graphics = this.bufferStrategy.getDrawGraphics();
    }

    public void update() {
        this.graphics.drawImage(this.image, 0, 0, this.canvas.getWidth(), this.canvas.getHeight(), null);
        this.bufferStrategy.show();
    }

    public BufferedImage getImage() {
        return this.image;
    }

    public Canvas getCanvas() {
        return this.canvas;
    }

    public JFrame getFrame() {
        return this.frame;
    }

}
