package by.chechetkin.command;

import by.chechetkin.Bot;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.awt.*;
import java.util.function.Consumer;

import static by.chechetkin.configuration.BotConstants.*;

public class HelpConsumer implements Consumer<CommandEvent> {
    private static final Logger LOGGER = LogManager.getLogger(HelpConsumer.class);

    @Override
    public void accept(CommandEvent event) {
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle(BOT_NAME, null)
                .setColor(Color.BLUE)
                .setDescription(HELP_CMD_DESCRIPTION)
                .setAuthor(AUTHOR_NAME, AUTHOR_URL);

        try {
            embedBuilder.setImage(HELP_IMAGE_URL);
        } catch (IllegalArgumentException ex) {
            LOGGER.warn("Failed to load image from url:" + HELP_IMAGE_URL);
        }

        Bot.COMMANDS.forEach(command -> embedBuilder.addField("!" + command.getName(),
                command.getDescription(), true));

        event.reply(embedBuilder.build());
    }
}
