package eu.betterattack.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class HttpException extends RuntimeException {

    private final int status;
    private final String message;

    public abstract String getGenericName();

}
