package org.example.command.context;

import org.example.common.CommandData;

public class PingCommandContex extends AbstractCommandContext{

    public PingCommandContex(CommandData commandData) {
        super(commandData);
    }

    @Override
    public byte[] buildResponse() {
        return writer.simpleString("PONG");
    }
}
