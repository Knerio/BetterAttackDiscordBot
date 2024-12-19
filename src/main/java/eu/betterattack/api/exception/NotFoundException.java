package eu.betterattack.api.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends HttpException {


    public NotFoundException(String message) {
        super(404, message);
    }

    @Override
    public String getGenericName() {
        return "Not Found";
    }
}