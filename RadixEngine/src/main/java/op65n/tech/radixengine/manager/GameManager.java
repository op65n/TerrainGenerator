package op65n.tech.radixengine.manager;

import op65n.tech.radixengine.GameObject;
import op65n.tech.radixengine.component.Container;
import op65n.tech.radixengine.component.Renderer;

public final class GameManager implements GameObject {

    private Runnable drawable;

    public void setDrawable(final Runnable runnable) {
        this.drawable = runnable;
    }

    @Override
    public void update(final Container container, final Renderer renderer) {
        renderer.clear();

        if (drawable != null)
            drawable.run();
    }

    @Override
    public void render(final Container container, final Renderer renderer) {
        renderer.clear();

        if (drawable != null)
            drawable.run();
    }

}
