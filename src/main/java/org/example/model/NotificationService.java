package org.example.model;


import java.util.*;

public class NotificationService {
    private final Map<EventType, List<EventListener>> listeners;

    public NotificationService() {
        listeners = new HashMap<>();
        Arrays.stream(EventType.values()).forEach(eventType -> {
            listeners.put(eventType, new ArrayList<>());
        });
    }

    public void subscribe(EventType eventType, EventListener listener) {
        listeners.get(eventType).add(listener);
    }

    public void unsubscribe(EventType eventType, EventListener listener) {
        listeners.get(eventType).remove(listener);
    }

    public void notify(EventType eventType) {
        listeners.get(eventType).forEach(listener -> {
            listener.update(eventType);
        });
    }
}
