package hu.lenartl.petstore.pet;

import com.fasterxml.jackson.annotation.JsonValue;

public enum PetStatus {
    AVAILABLE("available"),
    NOT_AVAILABLE("not_available");

    private final String displayValue;

    PetStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    @JsonValue
    public String getDisplayValue() {
        return displayValue;
    }
}
