package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.text.MessageFormat;
import java.util.List;

import static by.chechetkin.configuration.BotConstants.*;

public class UnMuteAllCommand extends DescriptiveCommand {
    public UnMuteAllCommand() {
        super.name = UNMUTE_ALL_CMD;
    }

    @Override
    public String getDescription() {
        return UNMUTE_ALL_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (checkPermissions(event)) return;

        List<Member> members = Util.getChannelMembers(event);

        if (members.size() > 1) {
            members.stream()
                    .filter(member -> !member.equals(event.getSelfMember()))
                    .forEach(member -> member.mute(false).complete());

            Util.sendInfo(event, OPERATION_SUCCESS_MSG,
                    MessageFormat.format(USERS_UNMUTED_MSG, members.size() - 1));
        }
    }

    private boolean checkPermissions(CommandEvent event) {
        if (!event.getMember().hasPermission(Permission.VOICE_MOVE_OTHERS)) {
            Util.sendWarning(event, LACK_OF_PERMISSIONS_MSG, LACK_OF_UNMUTE_PERMISSIONS_MSG);
            return true;
        }
        return false;
    }
}
