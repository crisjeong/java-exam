package com.example.eventdispatcher;

import java.util.HashMap;
import java.util.Map;

public class EventDispatcher {
    private final Map<String, EventHandler> eventHandlers = new HashMap<>();

    // Register event handlers for different event types
    public void registerHandler(String eventType, EventHandler handler) {
        eventHandlers.put(eventType, handler);
    }

    // Dispatch the event to the appropriate handler
    public boolean dispatchEvent(String eventType) {
        EventHandler handler = eventHandlers.get(eventType);
        boolean result = false;

        if (handler != null) {
            result = handler.handleEvent();
        } else {
            System.out.println("No handler registered for " + eventType);
        }
        return result;
    }
}
