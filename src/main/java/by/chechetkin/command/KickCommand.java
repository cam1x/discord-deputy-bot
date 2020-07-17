package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static by.chechetkin.configuration.BotConstants.*;

public class KickCommand extends DescriptiveCommand {
    public KickCommand() {
        super.name = KICK_CMD;
    }

    @Override
    public String getDescription() {
        return KICK_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        Member member = event.getMember();
        Member selfMember = event.getGuild().getSelfMember();
        List<Member> targets = event.getMessage().getMentionedMembers();

        String reason = event.getArgs().replaceAll(MENTION_PATTERN, "").trim();
        if (targets.isEmpty() || reason.isEmpty()) {
            Util.sendWarning(event, KICK_FAILED_MSG, NO_KICK_ARGS_MSG);
            return;
        }

        for (Member target : targets) {
            if (!member.hasPermission(Permission.KICK_MEMBERS) || !member.canInteract(target)) {
                Util.sendWarning(event, KICK_FAILED_MSG, USER_DONT_HAVE_PERMISSION_MSG);
                continue;
            }

            if (!selfMember.hasPermission(Permission.KICK_MEMBERS) || !selfMember.canInteract(target)) {
                Util.sendWarning(event, KICK_FAILED_MSG, BOT_DONT_HAVE_PERMISSION_MSG);
                continue;
            }

            String kickedInfo = String.format(KICKED_MSG, event.getAuthor(), reason);
            event.getGuild().kick(target, kickedInfo).queue();
        }
    }
}