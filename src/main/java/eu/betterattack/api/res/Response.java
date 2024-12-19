package eu.betterattack.api.res;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Jacksonized
@Setter
@ToString
public class Response<T> {

    private int status;
    private String message;
    private T data;

    public boolean isSuccess() {
        return status == 200;
    }
}
