package eu.betterattack.bot;

import eu.betterattack.config.Config;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;

import java.util.EnumSet;

import static net.dv8tion.jda.api.requests.GatewayIntent.*;

@Getter
public class DiscordBot {

    private final Config config;
    private final JDA jda;

    public DiscordBot(Config config) throws InterruptedException {
        this.config = config;
        JDABuilder jdaBuilder = JDABuilder
                .create(config.getBot().getToken(), EnumSet.of(GUILD_MEMBERS, GUILD_MESSAGES, GUILD_VOICE_STATES, GUILD_MODERATION, GUILD_MESSAGE_REACTIONS, MESSAGE_CONTENT, DIRECT_MESSAGES));
        this.jda = jdaBuilder
                .setStatus(OnlineStatus.ONLINE)
                .addEventListeners(this)
                .build();

        jda.awaitReady();
    }
}
