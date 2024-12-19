package eu.betterattack.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.jackson.Jacksonized;

import java.io.File;
import java.io.IOException;

@Jacksonized
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Config {

    BotConfig bot;

    @JsonProperty("rest-api")
    RestAPIConfig restAPIConfig;

    public static Config load(File configFile) {
        try {
            return new ObjectMapper().readValue(configFile, Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Jacksonized
    @Builder
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BotConfig {

        String token;

    }

    @Jacksonized
    @Builder
    @Getter
    @Setter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @NoArgsConstructor
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RestAPIConfig {

        Integer port;

        @JsonProperty("jwt-secret")
        String JwtSecret;

    }
}
