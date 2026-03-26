package org.example.command;

import org.example.command.context.EchoCommandContext;

import java.io.IOException;

public class EchoCommand implements Command<EchoCommandContext>{

    @Override
    public byte[] execute(EchoCommandContext context) throws IOException {
        return context.buildResponse();
    }
}
