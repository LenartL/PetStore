package hu.lenartl.petstore.exception;

public class JsonPatchMappingException extends RuntimeException {

    public JsonPatchMappingException() {
    }

    public JsonPatchMappingException(String message) {
        super(message);
    }
}
