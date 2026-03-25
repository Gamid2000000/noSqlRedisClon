package org.example.io;

import org.example.command.Command;
import org.example.common.Protocol;

public class Writer {
    public byte[] bulkString(String value){
        if (value == null) {
            return (Protocol.BULK_STRING.getSymbol() + "1" + Command.CRLF).getBytes();
        }
        return (Protocol.BULK_STRING.getSymbol() + value.length() + Command.CRLF
                + value + Command.CRLF).getBytes();
    }
    public byte[] simpleString(String value){
        return (Protocol.SIMPLE_STRING.getSymbol() + value + Command.CRLF).getBytes();
    }
    public byte[] simpleError(String value){
        return (Protocol.ERROR.getSymbol() + value + Command.CRLF).getBytes();
    }

    public byte[] integer(int value){
        return (Protocol.INTEGER.getSymbol() + value + Command.CRLF).getBytes();
    }
}
