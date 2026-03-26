package org.example.command.context;

import org.example.common.CommandData;

public class SetCommandContext extends AbstractCommandContext{

   private final String key;

   private final String value;

//   private final Long expiredTimeMs;

    public SetCommandContext(CommandData commandData) {
        super(commandData);

        if (commandData.partSize() < 3) {
            throw new IllegalArgumentException(
                    "SET command requires format: SET key value. Got: " + commandData.getParts()
            );
        }
        this.key = commandData.getPart(1);
        this.value = commandData.getPart(2);

    }

    @Override
    public byte[] buildResponse() {
        return writer.simpleString("OK");
    }
    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
}
