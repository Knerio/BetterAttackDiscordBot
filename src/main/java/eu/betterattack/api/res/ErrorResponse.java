package eu.betterattack.api.res;

import eu.betterattack.api.exception.HttpException;

public class ErrorResponse extends Response<Object> {

    public ErrorResponse(HttpException error) {
        super(error.getStatus(), error.getGenericName() + " - " + error.getMessage(), null);
    }

}
