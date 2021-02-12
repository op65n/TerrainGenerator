package op65n.tech.radixengine.component;

import op65n.tech.radixengine.GameObject;
import op65n.tech.radixengine.GameState;

public final class Container implements Runnable {

    private final GameObject gameObject;
    private final GameState gameState = new GameState();

    private int width = 300;
    private int height = 250;
    private float scale = 2.0F;

    private final Window window = new Window(this);
    private final Renderer renderer = new Renderer(this);

    public Container(final GameObject gameObject, final String application) {
        this.gameObject = gameObject;
        this.gameState.setApplication(application);
    }

    public void assignProperties(final int width, final int height, final float scale) {
        this.width = width;
        this.height = height;
        this.scale = scale;
    }

    public void start() {
        final Thread gameThread = new Thread(this);

        gameThread.start();
    }

    @Override
    public void run() {
        this.gameState.toggleRunning();
        this.gameObject.render(this, this.renderer);

        while (this.gameState.isRunning()) {
            this.gameState.setRendering(true);

            if (this.gameState.isRendering()) {
                this.renderer.clear();
                this.gameObject.update(this, this.renderer);
                this.window.update();
            }
        }
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

    public String getApplication() {
        return this.gameState.getApplication();
    }

    public Window getWindow() {
        return this.window;
    }

    public Renderer getRenderer() {
        return this.renderer;
    }
}
