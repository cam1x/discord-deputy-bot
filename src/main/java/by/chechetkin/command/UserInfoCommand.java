package by.chechetkin.command;

import by.chechetkin.util.Util;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.commons.utils.FinderUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static by.chechetkin.configuration.BotConstants.*;

public class UserInfoCommand extends DescriptiveCommand {
    public UserInfoCommand() {
        super.name = USER_INFO_CMD;
    }

    @Override
    public String getDescription() {
        return USER_INFO_CMD_DESCRIPTION;
    }

    @Override
    protected void execute(CommandEvent event) {
        String args = event.getArgs();
        if (args.isEmpty()) {
            Util.sendWarning(event, FAILED_TO_GET_INFO_MSG, USERNAME_NOT_PROVIDED_MSG);
            return;
        }

        String username = Util.removeMention(String.join("", args));

        List<User> foundUsers = FinderUtil.findUsers(username, event.getJDA());
        if (foundUsers.isEmpty()) {
            List<Member> foundMembers = FinderUtil.findMembers(username, event.getGuild());
            if (foundMembers.isEmpty()) {
                Util.sendWarning(event, FAILED_TO_GET_INFO_MSG, USER_NOT_FOUND_MSG);
                return;
            }
            foundUsers = foundMembers.stream()
                    .map(Member::getUser)
                    .collect(Collectors.toList());
        }

        User user = foundUsers.get(0);
        Member member = event.getGuild().getMember(user);

        MessageEmbed embed = new EmbedBuilder()
                .setColor(Objects.requireNonNull(member).getColor())
                .setThumbnail(user.getEffectiveAvatarUrl().replaceFirst("gif", "png"))
                .addField(USERNAME_DISCRIMINATOR_MSG, String.format("%#s", user), false)
                .addField(DISPLAY_NAME_MSG, member.getEffectiveName(), false)
                .addField(USER_ID_AND_MENTION_MSG, String.format("%s (%s)", user.getId(), member.getAsMention()), false)
                .addField(ACCOUNT_CREATED_MSG, member.getTimeCreated().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField(JOINED_TO_SERVER_MSG, member.getTimeJoined().format(DateTimeFormatter.RFC_1123_DATE_TIME), false)
                .addField(BOT_ACCOUNT_MSG, user.isBot() ? "Yes" : "No", false)
                .build();

        event.reply(embed);
    }
}
