package by.chechetkin.util;

import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import static by.chechetkin.configuration.BotConstants.BOT_NOT_CONNECTED_TO_VOICE_MSG;
import static by.chechetkin.configuration.BotConstants.OPERATION_FAILED_MSG;

public class Util {
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return NUMERIC_PATTERN.matcher(strNum).matches();
    }

    public static void sendWarning(CommandEvent event, String title, String message) {
        EmbedBuilder lackOfPermissions = Util.getWarningEmbedBuilder(title, message);
        event.reply(lackOfPermissions.build());
    }

    public static void sendInfo(CommandEvent event, String title, String message) {
        EmbedBuilder lackOfPermissions = Util.getInfoEmbedBuilder(title, message);
        event.reply(lackOfPermissions.build());
    }

    public static List<Member> getChannelMembers(CommandEvent event) {
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {
            sendWarning(event, OPERATION_FAILED_MSG, BOT_NOT_CONNECTED_TO_VOICE_MSG);
            return Collections.emptyList();
        }

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();
        List<Member> members = Objects.requireNonNull(voiceChannel).getMembers();
        if (!members.contains(event.getMember())) {
            sendWarning(event, OPERATION_FAILED_MSG, BOT_NOT_CONNECTED_TO_VOICE_MSG);
            return Collections.emptyList();
        }

        return members;
    }

    public static String removeMention(String username) {
        return (username.startsWith("@"))? username.substring(1): username;
    }

    private static EmbedBuilder getInfoEmbedBuilder(String title, String description) {
        return new EmbedBuilder()
                .setTitle(title, null)
                .setColor(Color.GREEN)
                .setDescription(description);
    }

    private static EmbedBuilder getWarningEmbedBuilder(String title, String description) {
        return new EmbedBuilder()
                .setTitle(title, null)
                .setColor(Color.RED)
                .setDescription(description);
    }
}
