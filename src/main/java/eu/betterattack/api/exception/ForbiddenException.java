package eu.betterattack.api.exception;

import lombok.Getter;

@Getter
public class ForbiddenException extends HttpException {


    public ForbiddenException(String message) {
        super(403, message);
    }

    @Override
    public String getGenericName() {
        return "Forbidden";
    }
}