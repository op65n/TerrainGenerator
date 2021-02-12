package op65n.tech.radixengine;

public final class GameState {

    private String application;
    private boolean running = false;
    private boolean render = false;

    public boolean isRunning() {
        return this.running;
    }

    public void toggleRunning() {
        this.running = !this.running;
    }

    public boolean isRendering() {
        return this.render;
    }

    public void setRendering(final boolean state) { this.render = state; }

    public String getApplication() { return this.application; }

    public void setApplication(final String application) {
        this.application = application;
    }
}
