package eu.betterattack.api.exception;

import lombok.Getter;

@Getter
public class BadRequestException extends HttpException {


    public BadRequestException(String message) {
        super(400, message);
    }

    @Override
    public String getGenericName() {
        return "Bad Request";
    }
}