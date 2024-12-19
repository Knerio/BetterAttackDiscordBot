package eu.betterattack.api.exception;

import lombok.Getter;

@Getter
public class InternalServerException extends HttpException {


    public InternalServerException(Exception e) {
        super(500, e.getMessage());
    }

    @Override
    public String getGenericName() {
        return "Internal Server Error";
    }
}
