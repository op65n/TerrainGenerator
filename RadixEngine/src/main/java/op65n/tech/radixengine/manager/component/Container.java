package op65n.tech.radixengine.manager.component;

import op65n.tech.radixengine.GameObject;
import op65n.tech.radixengine.GameState;
import op65n.tech.radixengine.manager.component.constraint.WindowConstraint;

public final class Container {

    private final Window window = new Window(this);
    private final Renderer renderer = new Renderer(this);
    private final WindowConstraint constraint;

    public Container(final GameObject object, final String application, final WindowConstraint constraint) {
        this.constraint = constraint;
    }

    public WindowConstraint getConstraint() {
        return this.constraint;
    }

}
