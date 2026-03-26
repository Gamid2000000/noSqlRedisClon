package org.example.common;

import org.apache.commons.lang3.tuple.Pair;
import org.example.command.CommandType;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class CommandData {
    private final String command;

    private final Long commandLentgh;

    private List<String> parts;

    private CommandData(String command, Long commandLentgh) {
        this.command = command;
        this.commandLentgh = commandLentgh;
    }
    public static CommandData of(String command, Long commandLentgh){
        return new CommandData(command, commandLentgh);
    }
    public String getCommand() {
        return command;
    }
    public Long getCommandLentgh() {
        return commandLentgh;
    }
    public List<String> getParts() {
        if (parts == null){
            parts = Arrays.stream(command.trim().split("\\s+")).toList();
        }
        return parts;
    }
    public String getPart(int index){
        return getParts().get(index);
    }
    public String getAllParts(int from){
        return String.join(" ", getParts().subList(from, getParts().size()));
    }

    public <T> T getPart(int index, Function<String, T> converter){
        try {
            return converter.apply(getPart(index));
        }
        catch (Exception e){
            throw new IllegalArgumentException("Can't convert part: " + e.getMessage());
        }
    }
    public Pair<String,Integer> getPartByName(String name){
        return getPartByName(name,String::valueOf);
    }
    public <T> Pair<T,Integer> getPartByName(String name, Function<String, T> converter){
        for (int i = 1; i < getParts().size(); i++) {
            var part = getPart(i);
            if (part.equalsIgnoreCase(name)){
                return Pair.of(converter.apply(part),i);
            }

        }
        return Pair.of(null,-1);
    }

    public Integer partSize(){
        return getParts().size();
    }

    public CommandType extractCommandType(){
        return CommandType.fromCommand(getPart(0));
    }

    public <T> T convertCommand(Function<String, T> converter){
        return converter.apply(command);
    }

}
