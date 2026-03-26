package org.example.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private final Map<String, CacheValue> cache;

    public Cache() {
        this.cache = new ConcurrentHashMap<>();
    }

    public Object put(String key,String value){
        var insertedValue = cache.put(key, new CacheValue(value));
        if(insertedValue == null){
            return null;
        }
        return insertedValue.getValue();
    }

    public CacheValue put(String key, String value, Long expiredDataMs){
        return cache.put(key, new CacheValue(value, expiredDataMs));
    }

    public CacheValue put(ValueType valueType,String key ,Object value){
        return cache.put(key, new CacheValue(valueType, value));
    }

    public CacheValue get(String key){
        var value = cache.get(key);
        if(value == null){
            return null;
        }
        if(value.isExpired()){
            cache.remove(key);
            return null;
        }
        return value;
    }

    public CacheValue get(String key, ValueType valueType){
        var value = get(key);
        if(value == null|| value.getValueType() != valueType){
            return null;
        }
        return value;
    }

    public <T> T getValue(String key, ValueType valueType, T defaultValue){
        var cacheValue = get(key);
        if (cacheValue !=null && cacheValue.getValueType() != valueType){
            return null;
        }
        if (cacheValue == null){
            return defaultValue;
        }
        return (T) cacheValue.getValue();
    }

    public <T> T getValue(String key, ValueType valueType){
        return getValue(key,valueType,null);
    }

    public Map<String,CacheValue> getAllData(){
        return cache;
    }
}
