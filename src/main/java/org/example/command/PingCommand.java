package org.example.command;

import org.example.command.context.PingCommandContex;

import java.io.IOException;

public class PingCommand implements Command<PingCommandContex>{
    @Override
    public byte[] execute(PingCommandContex context) throws IOException {
        return context.buildResponse();
    }
}
