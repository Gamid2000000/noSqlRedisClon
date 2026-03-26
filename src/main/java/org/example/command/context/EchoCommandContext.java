package org.example.command.context;

import org.example.common.CommandData;

public class EchoCommandContext  extends AbstractCommandContext{

    public EchoCommandContext(CommandData commandData) {
        super(commandData);
    }

    @Override
    public byte[] buildResponse() {
        return writer.simpleString(commandData.getAllParts(1));
    }
}
