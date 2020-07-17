package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;

import java.util.List;

import static by.chechetkin.configuration.BotConstants.*;

public class BanCommand extends DescriptiveCommand {
    public BanCommand() {
        super.name = BAN_CMD;
    }

    @Override
    public String getDescription() {
        return BAN_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        Member member = event.getMember();
        Member selfMember = event.getGuild().getSelfMember();
        List<Member> targets = event.getMessage().getMentionedMembers();

        String reason = event.getArgs().replaceAll(MENTION_PATTERN, "").trim();
        if (targets.isEmpty() || reason.isEmpty()) {
            Util.sendWarning(event, BAN_FAILED_MSG, NO_BAN_ARGS_MSG);
            return;
        }

        for (Member target : targets) {
            if (!member.hasPermission(Permission.BAN_MEMBERS) || !member.canInteract(target)) {
                Util.sendWarning(event, BAN_FAILED_MSG, USER_DONT_HAVE_PERMISSION_MSG);
                continue;
            }

            if (!selfMember.hasPermission(Permission.BAN_MEMBERS) || !selfMember.canInteract(target)) {
                Util.sendWarning(event, BAN_FAILED_MSG, BOT_DONT_HAVE_PERMISSION_MSG);
                continue;
            }

            String bannedInfo = String.format(BANNED_MSG, event.getAuthor(), reason);
            event.getGuild()
                    .ban(target, 1)
                    .reason(bannedInfo)
                    .queue();
        }
    }
}
