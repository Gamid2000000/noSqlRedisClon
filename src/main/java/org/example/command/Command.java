package org.example.command;

import org.example.command.context.CommandContex;

import java.io.IOException;

public interface Command <T extends CommandContex>{
    String CRLF = "\r\n";

    byte[] execute(T context) throws IOException;
}
