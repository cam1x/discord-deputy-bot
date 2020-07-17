package by.chechetkin.configuration;

public class BotConstants {
    public final static String SECURITY_PROPERTIES = "security.properties";
    public final static String TOKEN_PROPERTY = "security.token";
    public final static String OWNER_ID_PROPERTY = "security.owner";

    public final static String BOT_NAME = "Personal deputy";
    public final static String HELP_IMAGE_URL =
            "https://contenthub-static.grammarly.com/blog/wp-content/uploads/2018/05/how-to-ask-for-help.jpg";
    public final static String ACTIVITY_NAME = "to your orders";
    public final static String AUTHOR_NAME = "Maxim a.k.a cam1x";
    public final static String AUTHOR_URL = "https://t.me/max1my";

    public final static String MENTION_PATTERN = "\\<\\@\\!\\w+\\>";

    //Commands
    public final static String CMD_PREFIX = "!";
    public final static String HELP_CMD = "help";
    public final static String JOIN_CMD = "join";
    public final static String LEAVE_CMD = "leave";
    public final static String MUTE_ALL_CMD = "muteAll";
    public final static String UNMUTE_ALL_CMD = "unmuteAll";
    public final static String USER_INFO_CMD = "info";
    public final static String KICK_CMD = "kick";
    public final static String BAN_CMD = "ban";
    public final static String UNBAN_CMD = "unban";
    public final static String SEND_ALL_CMD = "sendAll";
    //Operation status
    public final static String OPERATION_SUCCESS_MSG = "Operation completed!";
    public final static String OPERATION_FAILED_MSG = "Operation failed";
    public final static String LACK_OF_PERMISSIONS_MSG = "Lack of permissions";
    public final static String USER_DONT_HAVE_PERMISSION_MSG = "You don't have permission to use this command";
    public final static String BOT_DONT_HAVE_PERMISSION_MSG = "I don't have permission to use this command";
    //Join messages
    public final static String BOT_ALREADY_CONNECTED_MSG = "Bot's already connected to a channel";
    public final static String USER_NOT_CONNECTED_TO_VOICE_MSG = "Please join a voice channel first";
    public final static String LACK_OF_JOIN_PERMISSIONS_MSG = "Not enough permissions to join ";
    public final static String BOT_JOINED_MSG = "Joining your voice channel";
    public final static String JOIN_FAILED_MSG = "Joining your voice channel";
    //Leave messages
    public final static String BOT_NOT_CONNECTED_TO_VOICE_MSG = "Bot is not connected to a voice channel";
    public final static String BOT_AND_USER_NOT_SAME_VOICE_MSG =
            "You have to be in the same voice channel as me to use this";
    public final static String BOT_LEAVED_MSG = "Disconnected from your channel";
    public final static String LEAVE_FAILED_MSG = "Leave failed";
    //Mute messages
    public final static String USERS_MUTED_MSG = "{0} users was muted";
    public final static String USERS_UNMUTED_MSG = "{0} users was unmuted";
    public final static String LACK_OF_MUTE_PERMISSIONS_MSG = "Mute failed";
    public final static String LACK_OF_UNMUTE_PERMISSIONS_MSG = "Unmute failed";
    //Kick messages
    public final static String NO_KICK_ARGS_MSG = "Missing kick reason or user not mentioned (not roles)";
    public final static String KICKED_MSG = "Kicked by: %#s, with reason: %s";
    public final static String KICK_FAILED_MSG = "Kick failed";
    //Ban messages
    public final static String NO_BAN_ARGS_MSG = "Missing ban reason or user not mentioned (not roles)";
    public final static String BANNED_MSG = "Ban by: %#s, with reason: %s";
    public final static String USER_NOT_BANNED_MSG = "This user is not banned";
    public final static String UNBANNED_MSG = "{0} unbanned by {1}";
    public final static String BAN_FAILED_MSG = "Ban failed";
    public final static String UNBAN_FAILED_MSG = "UnBan failed";
    //User info messages
    public final static String USERNAME_NOT_PROVIDED_MSG = "Username is required!";
    public final static String USER_NOT_FOUND_MSG = "User not found!";
    public final static String USERNAME_DISCRIMINATOR_MSG = "Username#Discriminator";
    public final static String DISPLAY_NAME_MSG = "Display Name";
    public final static String USER_ID_AND_MENTION_MSG = "User Id + Mention";
    public final static String ACCOUNT_CREATED_MSG = "Account Created";
    public final static String JOINED_TO_SERVER_MSG = "Joined to server";
    public final static String BOT_ACCOUNT_MSG = "Bot account";
    public final static String FAILED_TO_GET_INFO_MSG = "Leave failed";
    //Send all messages
    public final static String LACK_OF_WRITE_PERMISSIONS_MSG = "To complete this operation write permission is required";
    public final static String SENT_TO_ALL_MSG = "All users got message";
    //Commands description
    public final static String HELP_CMD_DESCRIPTION = "Bot that's making your experience easier";
    public final static String JOIN_CMD_DESCRIPTION = "Joins to user's voice channel";
    public final static String LEAVE_CMD_DESCRIPTION = "Leaves voice channel";
    public final static String MUTE_ALL_CMD_DESCRIPTION = "Mutes all users in voice channel " +
            "(optionally time of muting in seconds can be provided - !muteAll 10)";
    public final static String UNMUTE_ALL_CMD_DESCRIPTION = "Unmutes all users in voice channel";
    public final static String USER_INFO_CMD_DESCRIPTION = "Shows user's info";
    public final static String KICK_CMD_DESCRIPTION = "Kicks user from server";
    public final static String BAN_CMD_DESCRIPTION = "Bans user";
    public final static String UNBAN_CMD_DESCRIPTION = "Unbans user";
    public final static String SEND_ALL_CMD_DESCRIPTION = "Sends message to all users in voice channel";

    private BotConstants() {
        throw new AssertionError("Constructor of BotConstants calling is forbidden!");
    }
}
