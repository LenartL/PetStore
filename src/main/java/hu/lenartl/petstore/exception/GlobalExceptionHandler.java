package hu.lenartl.petstore.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler implements ErrorController {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String orderNotFoundHandler(OrderNotFoundException e) {
        return messageSource.getMessage("exception.order.not-found", null, Locale.getDefault());
    }

    @ExceptionHandler(JsonPatchMappingException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String orderNotFoundHandler(JsonPatchMappingException e) {
        return messageSource.getMessage("exception.json.patch", null, Locale.getDefault());
    }

    @ExceptionHandler({NoSuchElementException.class, IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidOrderHandler(RuntimeException e) {
        return messageSource.getMessage("exception.order.invalid", null, Locale.getDefault());
    }

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String defaultErrorHandler(Throwable t) {
        return messageSource.getMessage("exception.unclassified", null, Locale.getDefault());
    }
}
