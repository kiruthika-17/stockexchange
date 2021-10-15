package com.model.stockexchange.dto;

public enum StatusEnum {

    PLACED((byte)1),
    PENDING((byte)2),
    COMPLETED((byte)3);

    private Byte id;

    StatusEnum (Byte id) {
        this.id = id;
    }

    public byte getId() {
        return id;
    }

    public static StatusEnum getEnumByValue(Byte id) {
        for (StatusEnum e : StatusEnum.values()) {
            if (e.getId() == id) {
                return e;
            }
        }
        return null;
    }
}
