package hu.lenartl.petstore.order;

import com.fasterxml.jackson.annotation.JsonValue;

public enum OrderStatus {
    PLACED("placed"),
    APPROVED("approved"),
    SHIPPED("shipped");

    private final String displayValue;

    OrderStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonValue
    public String getDisplayValue() {
        return displayValue;
    }
}
