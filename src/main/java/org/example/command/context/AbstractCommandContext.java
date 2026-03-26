package org.example.command.context;

import org.example.common.CommandData;
import org.example.common.ObjectHolder;
import org.example.io.Writer;

public abstract class AbstractCommandContext implements CommandContex {

    protected CommandData commandData;

    protected final Writer writer;

    public AbstractCommandContext(CommandData commandData) {
        this.writer = ObjectHolder.getInstance().getObject(Writer.class);
        this.commandData = commandData;

    }
}
