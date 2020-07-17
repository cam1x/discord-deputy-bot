package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

import static by.chechetkin.configuration.BotConstants.*;


public class SendAllCommand extends DescriptiveCommand {
    public SendAllCommand() {
        super.name = SEND_ALL_CMD;
    }

    @Override
    public String getDescription() {
        return SEND_ALL_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        if (checkPermissions(event)) return;

        List<Member> members = Util.getChannelMembers(event);
        if (members.size() > 1) {
            String message = event.getArgs();

            members.stream()
                    .filter(member -> !member.equals(event.getSelfMember()))
                    .forEach(member -> sendPrivateMessage(member, message));

            Util.sendInfo(event, OPERATION_SUCCESS_MSG, SENT_TO_ALL_MSG);
        }
    }

    private boolean checkPermissions(CommandEvent event) {
        if (!event.getMember().hasPermission(Permission.MESSAGE_WRITE)) {
            Util.sendWarning(event, LACK_OF_PERMISSIONS_MSG, LACK_OF_WRITE_PERMISSIONS_MSG);
            return true;
        }
        return false;
    }

    private void sendPrivateMessage(Member member, String content) {
        member.getUser()
                .openPrivateChannel()
                .queue((channel) -> channel.sendMessage(content).queue());
    }
}
