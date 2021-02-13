package op65n.tech.radixengine;

import op65n.tech.radixengine.view.GameWindow;
import op65n.tech.radixengine.view.component.WindowConstraint;
import op65n.tech.radixengine.view.component.WindowProperty;
import op65n.tech.radixengine.view.component.draw.Renderer;

import java.awt.*;

public final class GameWindowTest {

    public static void main(final String[] input) {
        final GameWindow window = new GameWindow(
                "Test Application",
                new WindowConstraint.WindowBuilder().build(),
                new WindowProperty.WindowPropertyBuilder().build()
        );

        window.start(consumer -> {
            final Renderer renderer = consumer.getDisplay().getRenderer();

            renderer.drawText("Test Text", 10, 10, Color.BLACK);
            renderer.drawRectangle(15, 10, 10, 10, Renderer.RectangleType.EMPTY, Color.BLUE);
        });
    }

}
