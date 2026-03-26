package org.example.command;

import org.example.cache.Cache;
import org.example.command.context.CommandContex;
import org.example.command.context.SetCommandContext;
import org.example.common.ObjectHolder;

import java.io.IOException;

public class SetCommand implements Command<SetCommandContext> {

    @Override
    public byte[] execute(SetCommandContext context) throws IOException {
        var key = context.getKey();
        var value = context.getValue();
        var cache = ObjectHolder.getInstance().getObject(Cache.class);

        cache.put(key,value);
        return context.buildResponse();
    }
}
