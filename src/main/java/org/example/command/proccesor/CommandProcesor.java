package org.example.command.proccesor;

import org.example.command.Command;
import org.example.command.CommandType;
import org.example.command.PingCommand;
import org.example.command.context.CommandContex;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProcesor {
    private final Map<CommandType, Command<?>> commands;

    public CommandProcesor() {
        commands = new ConcurrentHashMap<>();
        commands.put(CommandType.PING,new PingCommand());
    }
    @SuppressWarnings("unchecked")
    public <T extends CommandContex> byte[] procces(T context, CommandType commandType)throws IOException {
        Command<T> command = (Command<T>) commands.get(commandType);
        if (command != null){
            return command.execute(context);
        }
        else {
            throw new IllegalArgumentException("Unknown command type: " + commandType);
        }
    }
}
