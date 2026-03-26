package org.example.cache;

import org.apache.commons.lang3.math.NumberUtils;

import java.time.Instant;
import java.util.function.Function;

public class CacheValue {
    private final ValueType valueType;

    private final Object value;

    private Instant expiredData;

    public CacheValue(String value) {
        this.value = value;
        this.valueType = ValueType.STRING;
    }

    public CacheValue(ValueType valueType, Object value) {
        this.valueType = valueType;
        this.value = value;
    }

    public CacheValue(ValueType valueType, Object value, Instant expiredData) {
        this.valueType = valueType;
        this.value = value;
        this.expiredData = expiredData;
    }
    public CacheValue(String value, Long expiredDataMs) {
        this.value = value;
        this.expiredData = Instant.now().plusMillis(expiredDataMs);
        this.valueType = ValueType.STRING;
    }

    public Object getValue() {
        return value;
    }

    @SuppressWarnings("unchecked")
    public <T> T getTypedValue(){
        return (T) getValue();
    }

    public <T> T getTypedValue(Function<String, T> converter){
        return converter.apply(getTypedValue());
    }

    public Instant getExpiredData() {
        return expiredData;
    }

    public void setExpiredData(Instant expiredData) {
        this.expiredData = expiredData;
    }

    public boolean isExpired(){
        return expiredData != null && Instant.now().isAfter(expiredData);
    }

    public ValueType getValueType() {
        return valueType;
    }

    public boolean isNotNumber(){
        return !NumberUtils.isCreatable(getTypedValue());
    }

}


