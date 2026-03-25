package org.example.handler;

import org.example.command.CommandType;
import org.example.command.context.CommandContex;
import org.example.command.context.PingCommandContex;
import org.example.command.proccesor.CommandProcesor;
import org.example.common.CommandData;
import org.example.common.ObjectHolder;
import org.example.exeption.ProccesExeption;
import org.example.io.Reader;
import org.example.io.Writer;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public abstract class AbstractHandler implements Handler{

    protected final CommandProcesor commandProcesor;

    protected final Reader reader;

    protected final Writer writer;

    private final Map<CommandType, Function<CommandData, CommandContex>> contexts = new HashMap<>();

    public AbstractHandler() {
        this.commandProcesor = new CommandProcesor();
        this.reader = ObjectHolder.getInstance().getObject(Reader.class);
        this.writer = ObjectHolder.getInstance().getObject(Writer.class);
        contexts.put(CommandType.PING, PingCommandContex::new);
    }

    @Override
    public void handle(DataInputStream inputStream, OutputStream outputStream) throws IOException {
        while (true){
            CommandData commandData = reader.read(inputStream);
            var commandType = commandData.getCommandType();
            var context = contexts.get(commandType).apply(commandData);

            Supplier<byte[]> command = () ->{
                try {
                    return commandProcesor.procces(context,commandType);
                }
                catch (IOException e) {
                    throw new ProccesExeption(e);
                }
            };
            proccesResponse(command.get(),commandData,inputStream,outputStream);
        }
    }
    protected abstract void proccesResponse(byte[] response,
                                            CommandData commandData,
                                            DataInputStream inputStream,
                                            OutputStream outputStream) throws IOException;
}
