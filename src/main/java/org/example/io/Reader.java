package org.example.io;

import org.example.common.CommandData;
import org.example.common.Protocol;
import org.example.exeption.ReaderExeption;

import java.io.*;

public class Reader {
    public CommandData read(DataInputStream inputStream) {
        try{
            char c =  (char) inputStream.readByte();

            var parserResult = switch (c){
                case '*' -> readArray(inputStream);
                case '$' -> readBulkString(inputStream);
                case '+' -> readSimpleString(inputStream);
                default -> throw new ReaderExeption("Unknown symbol: " + c);
            };
            return CommandData.of(parserResult.getCommand().trim(),
                    parserResult.getCommandLentgh()+1);
        }
        catch (EOFException e){
            throw new ReaderExeption(e);
        }
        catch (IOException e) {
            throw new ReaderExeption(e);
        }
    }
    public CommandData readArray(DataInputStream inputStream)throws IOException {
        CommandData arrayLength = readSimpleString(inputStream);
        int length = arrayLength.convertCommand(Integer::valueOf);
        StringBuilder commandBuilder = new StringBuilder();
        Long totalLength = arrayLength.getCommandLentgh();

        for (int i = 0; i < length; i++) {
            CommandData parsedCommand = read(inputStream);
            commandBuilder.append(parsedCommand.getCommand()).append(" ");
            totalLength += parsedCommand.getCommandLentgh();
        }
        return CommandData.of(commandBuilder.toString().trim(), totalLength);
    }
    public CommandData readBulkString(DataInputStream inputStream)throws IOException{
        CommandData parsedResult = readSimpleString(inputStream);
        int stringLength = parsedResult.convertCommand(Integer::valueOf);
        StringBuilder stringBuilder = new StringBuilder();
        long commandLength = parsedResult.getCommandLentgh();
        for (int i = 0; i < stringLength; i++) {
            char c = (char) inputStream.readByte();
            commandLength++;

        }
        inputStream.skip(2);
        commandLength += 2;

        return CommandData.of(stringBuilder.toString(), commandLength);
    }
    public CommandData readSimpleString(DataInputStream inputStream)throws IOException{
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        long commandLength = 1L;
        int b;
        while ((b = inputStream.readByte()) != '\r'){
            buffer.write(b);
            commandLength++;
        }
        inputStream.readByte();
        commandLength++;

        return CommandData.of(buffer.toString(), commandLength);
    }

    public  String readFully(DataInputStream inputStream) throws IOException{
        char c = (char) inputStream.readByte();
        if (c!= Protocol.BULK_STRING.getSymbol().charAt(0)){
            throw new ReaderExeption("Unexpected charecter"+ c);
        }

        int sringLength = readSimpleString(inputStream).convertCommand(Integer::valueOf);
        byte[] bytes = new byte[sringLength];
        inputStream.readFully(bytes);
        return new String(bytes);
    }
}
