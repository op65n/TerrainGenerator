package op65n.tech.radixengine.execute;

public abstract class WindowExecutor implements Runnable {

    public abstract void start();

    public abstract void update();

    public abstract void stop();

}
