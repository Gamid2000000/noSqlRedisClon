package org.example.command;

import java.util.Arrays;

public enum CommandType {
    PING("ping");

    private final String command;

    CommandType(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public static CommandType fronCommand(String currentCommand){
        return Arrays.stream(CommandType.values())
                .filter(it -> it.command.equalsIgnoreCase(currentCommand))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown command: " + currentCommand));
    }
}
