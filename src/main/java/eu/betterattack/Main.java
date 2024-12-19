package eu.betterattack;

import eu.betterattack.api.RestAPIHandler;
import eu.betterattack.bot.DiscordBot;
import eu.betterattack.config.Config;
import lombok.Getter;

import java.io.File;
import java.util.TimeZone;

public class Main {

    @Getter
    private static DiscordBot bot;

    public static void main(String[] args) throws InterruptedException {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Berlin"));

        File configFile = new File(".", "credentials.json");
        Config config = Config.load(configFile);
        Main.bot = new DiscordBot(config);
        new RestAPIHandler(config, Main.bot);
    }
}