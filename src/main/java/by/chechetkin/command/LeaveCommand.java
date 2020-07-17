package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

import static by.chechetkin.configuration.BotConstants.*;

public class LeaveCommand extends DescriptiveCommand {
    public LeaveCommand() {
        super.name = LEAVE_CMD;
    }

    @Override
    public String getDescription() {
        return LEAVE_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        MessageChannel channel = event.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (!audioManager.isConnected()) {
            Util.sendWarning(event, LEAVE_FAILED_MSG, BOT_NOT_CONNECTED_TO_VOICE_MSG);
            return;
        }

        VoiceChannel voiceChannel = audioManager.getConnectedChannel();
        if (!Objects.requireNonNull(voiceChannel).getMembers().contains(event.getMember())) {
            Util.sendWarning(event, LEAVE_FAILED_MSG, BOT_AND_USER_NOT_SAME_VOICE_MSG);
            return;
        }

        audioManager.closeAudioConnection();
        channel.sendMessage(BOT_LEAVED_MSG).queue();
    }
}
