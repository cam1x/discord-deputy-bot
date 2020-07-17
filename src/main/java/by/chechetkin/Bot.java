package by.chechetkin;

import by.chechetkin.command.*;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static by.chechetkin.configuration.BotConstants.*;

public class Bot {
    public static final Set<DescriptiveCommand> COMMANDS = new HashSet<>();
    private static final Logger LOGGER = LogManager.getLogger(Bot.class);
    private static String token;
    private static String ownerId;

    public static void main(String[] args) throws LoginException {
        long enable = System.currentTimeMillis();
        init();
        LOGGER.info("Bot enabled in " +
                TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - enable) + " s!");
    }

    private static void init() throws LoginException {
        readSecurity();
        JDA jda = JDABuilder.createDefault(token).build();

        CommandClientBuilder clientBuilder = new CommandClientBuilder();
        initClientBuilder(clientBuilder);

        CommandClient commandClient = clientBuilder.build();
        addCommands(commandClient);

        jda.addEventListener(commandClient);
    }

    private static void readSecurity() {
        try (InputStream inputStream = Bot.class.getClassLoader().getResourceAsStream(SECURITY_PROPERTIES)) {
            Properties properties = new Properties();
            properties.load(inputStream);

            token = properties.getProperty(TOKEN_PROPERTY);
            ownerId = properties.getProperty(OWNER_ID_PROPERTY);
        } catch (IOException ex) {
            LOGGER.error("Failed to load properties!", ex);
        }
    }

    private static void initClientBuilder(CommandClientBuilder clientBuilder) {
        clientBuilder.setPrefix(CMD_PREFIX);
        clientBuilder.setHelpWord(HELP_CMD);
        clientBuilder.setOwnerId(ownerId);
        clientBuilder.setActivity(Activity.listening(ACTIVITY_NAME));
        clientBuilder.setHelpConsumer(new HelpConsumer());
    }

    private static void addCommands(CommandClient commandClient) {
        COMMANDS.add(new JoinCommand());
        COMMANDS.add(new LeaveCommand());
        COMMANDS.add(new UserInfoCommand());
        COMMANDS.add(new SendAllCommand());
        COMMANDS.add(new MuteAllCommand());
        COMMANDS.add(new UnMuteAllCommand());
        COMMANDS.add(new KickCommand());
        COMMANDS.add(new BanCommand());
        COMMANDS.add(new UnBanCommand());

        COMMANDS.forEach(commandClient::addCommand);
    }
}
