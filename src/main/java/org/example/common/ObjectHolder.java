package org.example.common;

import org.example.cache.Cache;
import org.example.io.Reader;
import org.example.io.Writer;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ObjectHolder {

    private static  volatile ObjectHolder INSTANCE;

    private final Map<Class<?>, Object> instances;

    private ObjectHolder(){
        var cache = new Cache();
        var writer = new Writer();
        this.instances = new HashMap<>();
        instances.put(Cache.class,cache);
        instances.put(Writer.class,writer);
        instances.put(Reader.class,new Reader());
    }

    public static ObjectHolder getInstance(){
        if (INSTANCE == null){
            synchronized (ObjectHolder.class){
                if (INSTANCE == null){
                    INSTANCE = new ObjectHolder();
                }
            }
        }
        return INSTANCE;
    }

    @SuppressWarnings("unchecked")
    public <T> T getObject(Class<T> type){
        var object = instances.get(Objects.requireNonNull(type));
        if(object == null){
            throw new IllegalArgumentException("No object found for type" + type.getSimpleName());
        }
        return (T) object;
    }


}
