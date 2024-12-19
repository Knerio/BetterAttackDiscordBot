package eu.betterattack.api.res;

public class SuccessResponse<T> extends Response<T> {

    public SuccessResponse(String message, T data) {
        super(200, message, data);
    }

    public SuccessResponse(T data) {
        super(200, null, data);
    }
}
