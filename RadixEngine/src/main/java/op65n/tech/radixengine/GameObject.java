package op65n.tech.radixengine;

import javax.swing.*;

public abstract class GameObject extends JFrame {

    public abstract void start(Runnable runnable);

    public abstract void stop(Runnable runnable);

}
