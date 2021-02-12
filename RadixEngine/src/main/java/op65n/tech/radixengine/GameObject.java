package op65n.tech.radixengine;

import op65n.tech.radixengine.component.Container;
import op65n.tech.radixengine.component.Renderer;

public interface GameObject {

    void update(Container container, Renderer renderer);

    void render(Container container, Renderer renderer);

}
