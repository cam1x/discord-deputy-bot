package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

import java.util.Objects;

import static by.chechetkin.configuration.BotConstants.*;

public class JoinCommand extends DescriptiveCommand {
    public JoinCommand() {
        super.name = JOIN_CMD;
    }

    @Override
    public String getDescription() {
        return JOIN_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        MessageChannel channel = event.getChannel();
        AudioManager audioManager = event.getGuild().getAudioManager();

        if (audioManager.isConnected()) {
            Util.sendWarning(event, JOIN_FAILED_MSG, BOT_ALREADY_CONNECTED_MSG);
            return;
        }

        GuildVoiceState memberVoiceState = event.getMember().getVoiceState();
        if (!Objects.requireNonNull(memberVoiceState).inVoiceChannel()) {
            Util.sendWarning(event, JOIN_FAILED_MSG, USER_NOT_CONNECTED_TO_VOICE_MSG);
            return;
        }

        VoiceChannel voiceChannel = memberVoiceState.getChannel();
        Member selfMember = event.getGuild().getSelfMember();
        if (!selfMember.hasPermission(Objects.requireNonNull(voiceChannel), Permission.VOICE_CONNECT)) {
            Util.sendWarning(event, JOIN_FAILED_MSG, LACK_OF_JOIN_PERMISSIONS_MSG + voiceChannel.getName());
            return;
        }

        audioManager.openAudioConnection(voiceChannel);
        channel.sendMessage(BOT_JOINED_MSG).queue();
    }
}