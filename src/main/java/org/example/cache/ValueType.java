package org.example.cache;

public enum ValueType {
    NONE("none"),
    STRING("string");

    private final String keyType;

    ValueType(String keyType) {
        this.keyType = keyType;
    }

    public String getKeyType() {
        return keyType;
    }
}
