package org.example.command.context;

import org.example.cache.CacheValue;
import org.example.cache.ValueType;
import org.example.command.Command;
import org.example.common.CommandData;
import org.example.common.Protocol;

public class GetCommandContext extends AbstractCommandContext{

    private final String key;

    private CacheValue cacheData;

    public GetCommandContext(CommandData commandData) {
        super(commandData);
        this.key = commandData.getPart(1);
    }

    @Override
    public byte[] buildResponse() {
        if (cacheData == null || cacheData.getValueType()!= ValueType.STRING){
            return (Protocol.BULK_STRING.getSymbol()+"-1"+ Command.CRLF).getBytes();

        }
        return writer.simpleString(cacheData.getTypedValue());
    }

    public void setCacheData(CacheValue cacheData) {
        this.cacheData = cacheData;
    }
    public String getKey() {
        return key;
    }
}
