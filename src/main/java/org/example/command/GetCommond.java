package org.example.command;

import org.example.cache.Cache;
import org.example.command.context.GetCommandContext;
import org.example.common.ObjectHolder;

import java.io.IOException;

public class GetCommond implements Command<GetCommandContext>{

    @Override
    public byte[] execute(GetCommandContext context) throws IOException {
        var cache = ObjectHolder.getInstance().getObject(Cache.class);
        var key = context.getKey();
        var data = cache.get(key);

        context.setCacheData(data);

        return context.buildResponse();

    }
}
