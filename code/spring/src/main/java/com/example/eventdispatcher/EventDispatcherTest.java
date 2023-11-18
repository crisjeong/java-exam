package com.example.eventdispatcher;

import java.nio.ByteBuffer;

public class EventDispatcherTest {
    public static void main(String[] args) {
        // Create event handlers (as instances of EventHandler)
        EventHandler mouseClickHandler = () -> {
            System.out.println("Mouse click event handled");
            return true;
        };

        EventHandler keyboardPressHandler = () -> {
            System.out.println("Keyboard press event handled");
            return true;
        };

        // Create an EventDispatcher
        EventDispatcher eventDispatcher = new EventDispatcher();

        // Register event handlers with the dispatcher
        eventDispatcher.registerHandler("MouseClick", mouseClickHandler);
        eventDispatcher.registerHandler("KeyboardPress", keyboardPressHandler);

        // Simulate events
        boolean result;

        result = eventDispatcher.dispatchEvent("MouseClick");
        result = eventDispatcher.dispatchEvent("KeyboardPress");
        result = eventDispatcher.dispatchEvent("UnknownEvent");
    }
}
