package eu.betterattack.api.exception;

import lombok.Getter;

@Getter
public class UnauthorizedException extends HttpException {


    public UnauthorizedException(String message) {
        super(401, message);
    }

    @Override
    public String getGenericName() {
        return "Unauthorized";
    }

}