package eu.betterattack.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import eu.betterattack.api.exception.ForbiddenException;
import eu.betterattack.api.exception.HttpException;
import eu.betterattack.api.exception.InternalServerException;
import eu.betterattack.api.exception.UnauthorizedException;
import eu.betterattack.api.link.LinkHandler;
import eu.betterattack.api.res.ErrorResponse;
import eu.betterattack.bot.DiscordBot;
import eu.betterattack.config.Config;
import io.javalin.Javalin;
import io.javalin.http.HandlerType;
import io.javalin.json.JavalinJackson;

import java.util.Date;

public class RestAPIHandler {

    private final Config config;
    private final DiscordBot bot;
    private final Javalin javalin;
    private final String secret;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public static final String PREFIX = "/betterattack-bot";

    public RestAPIHandler(Config config, DiscordBot bot) {
        this.config = config;
        this.bot = bot;
        this.secret = config.getRestAPIConfig().getJwtSecret();
        this.algorithm = Algorithm.HMAC256(secret);
        this.verifier = JWT.require(algorithm).build();

        ObjectMapper mapper = new ObjectMapper();
        this.javalin = Javalin.create(javalinConfig -> {
                    SimpleModule module = new SimpleModule();
                    mapper.registerModule(module);

                    javalinConfig.jsonMapper(new JavalinJackson(mapper, false));
                    javalinConfig.bundledPlugins.enableCors(corsPluginConfig -> {
                        corsPluginConfig.addRule(corsRule -> {
                            corsRule.reflectClientOrigin = true;
                            corsRule.allowCredentials = true;
                        });
                    });
                })
                .before(ctx -> {
                    if (ctx.method().equals(HandlerType.OPTIONS)) return;
                    if (ctx.queryParam("to-string") != null) {
                        mapper.configure(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS.mappedFeature(), true);
                    }

                    String token = ctx.header("Authorization");

                    if (token == null || !token.startsWith("Bearer ")) {
                        throw new UnauthorizedException("No token given");
                    }

                    token = token.replaceFirst("Bearer ", "");
                    try {
                        DecodedJWT decoded = verifier.verify(token);
                        if (decoded.getExpiresAt() == null) return;
                        if (decoded.getExpiresAt().before(new Date())) throw new ForbiddenException("Token is expired");
                    } catch (Exception _) {
                        throw new ForbiddenException("Invalid Token");
                    }

                })
                .options("*", ctx -> {
                    ctx.header("Access-Control-Allow-Origin", "*");
                    ctx.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
                })
                .after(ctx -> {
                    ctx.header("Access-Control-Allow-Headers", "Content-Type,Authorization");
                })
                .exception(Exception.class, (e, _) -> {
                    throw new InternalServerException(e);
                })
                .exception(HttpException.class, (e, ctx) -> {
                    ctx.status(e.getStatus()).json(new ErrorResponse(e));
                })
                .start(config.getRestAPIConfig().getPort());

        new LinkHandler(bot, javalin);

    }
}
