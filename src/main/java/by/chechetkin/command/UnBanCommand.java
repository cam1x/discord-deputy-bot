package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

import static by.chechetkin.configuration.BotConstants.*;

public class UnBanCommand extends DescriptiveCommand {
    public UnBanCommand() {
        super.name = UNBAN_CMD;
    }

    @Override
    public String getDescription() {
        return UNBAN_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        String args = event.getArgs();
        if (args.isEmpty()) {
            Util.sendWarning(event, UNBAN_FAILED_MSG, NO_BAN_ARGS_MSG);
            return;
        }

        if (!event.getMember().hasPermission(Permission.BAN_MEMBERS)) {
            Util.sendWarning(event, UNBAN_FAILED_MSG, USER_DONT_HAVE_PERMISSION_MSG);
            return;
        }

        if (!event.getGuild().getSelfMember().hasPermission(Permission.BAN_MEMBERS)) {
            Util.sendWarning(event, UNBAN_FAILED_MSG, BOT_DONT_HAVE_PERMISSION_MSG);
            return;
        }

        String argsJoined = Util.removeMention(String.join(" ", args));

        event.getGuild().retrieveBanList().queue(bans -> {
            List<User> goodUsers = bans.stream()
                    .filter((ban) -> isCorrectUser(ban, argsJoined))
                    .map(Guild.Ban::getUser)
                    .collect(Collectors.toList());

            if (goodUsers.isEmpty()) {
                Util.sendWarning(event, UNBAN_FAILED_MSG, USER_NOT_BANNED_MSG);
                return;
            }

            User target = goodUsers.get(0);

            String author = String.format("%#s", event.getAuthor());
            String bannedUser = String.format("%#s", target);
            String unbanInfo = MessageFormat.format(UNBANNED_MSG, bannedUser, author);
            event.getGuild()
                    .unban(target)
                    .reason(unbanInfo)
                    .queue();
        });
    }

    private boolean isCorrectUser(Guild.Ban ban, String arg) {
        User bannedUser = ban.getUser();

        return bannedUser.getName().equalsIgnoreCase(arg) || bannedUser.getId().equals(arg)
                || String.format("%#s", bannedUser).equalsIgnoreCase(arg);
    }
}
