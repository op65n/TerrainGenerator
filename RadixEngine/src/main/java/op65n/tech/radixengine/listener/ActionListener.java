package op65n.tech.radixengine.listener;

import op65n.tech.radixengine.listener.event.EventBus;

import java.awt.event.ActionEvent;
import java.util.Set;

public final class ActionListener implements java.awt.event.ActionListener {

    @Override
    public void actionPerformed(final ActionEvent event) {
        final Set<EventBus.EventComponent> optionalComponent = EventBus.getMatchingComponents(event);

        optionalComponent.forEach(it -> {
            final Runnable execution = it.getCodeExecution();

            if (execution != null)
                execution.run();
        });
    }

}
