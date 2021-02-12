package op65n.tech.radixengine.listener.event;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public final class EventBus {

    private static final List<EventComponent> COMPONENTS = new ArrayList<>();

    public static void addComponent(final EventComponent component) {
        COMPONENTS.add(component);
    }

    public static EventComponent removeComponent(final String identifier) {
        final Optional<EventComponent> optionalComponent = COMPONENTS.stream()
                .filter(comp -> comp.getIdentifier().equals(identifier))
                .findAny();

        if (optionalComponent.isEmpty()) return null;
        final EventComponent component = optionalComponent.get();

        return COMPONENTS.remove(component) ? component : null;
    }

    public static EventComponent removeComponent(final String identifier, final ActionEvent event) {
        final Optional<EventComponent> optionalComponent = COMPONENTS.stream()
                .filter(comp -> comp.getIdentifier().equals(identifier))
                .filter(comp -> comp.getEvent() == event)
                .findAny();

        if (optionalComponent.isEmpty()) return null;
        final EventComponent component = optionalComponent.get();

        return COMPONENTS.remove(component) ? component : null;
    }

    public static Set<EventComponent> getMatchingComponents(final ActionEvent event) {
        return COMPONENTS.stream()
                .filter(comp -> comp.getEvent() == event)
                .collect(Collectors.toSet());
    }

    public List<EventComponent> getComponents() {
        return COMPONENTS;
    }

    public static class EventComponent {

        private final String identifier;
        private final ActionEvent event;
        private final Runnable runnable;

        public EventComponent(final String identifier, final ActionEvent event, final Runnable runnable) {
            this.identifier = identifier;
            this.event = event;
            this.runnable = runnable;
        }

        public String getIdentifier() {
            return this.identifier;
        }

        public ActionEvent getEvent() {
            return this.event;
        }

        public Runnable getCodeExecution() { return this.runnable; }

    }

}
