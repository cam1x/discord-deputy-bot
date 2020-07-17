# Personal deputy
Discord bot simplifying channel administration 

## Import to your channel
1. [Link for joining](https://discord.com/api/oauth2/authorize?client_id=733324010158424124&permissions=8&scope=bot)
2. (optionally one of the steps)
  - create ***security.properties*** file in ***src/main/java/.../resources*** </br>
  and add security.token=*your token*, security.owner=*your owner id* </br>
  *P.S. you can change property file as name of properties by changing constants in BotConstant class.java*
  - add token and owner id to the appropriate places in Bot.java 

### Available commands
* !ban (@users) (reason) - Bans mentioned users for 1 day
* !clear (num) - deletes last num messages from char, if num not indicated deletes last 10
* !info (@user) - shows account info of mentioned user
* !join - joins to user's voice channel
* !kick (@users) (reason) - kickes mentioned users from the server
* !leave - leaves voice channel
* !mute - mutes mentioned users, optionally can be provided time of muting is seconds - !mute 10
* !muteAll - mutes all users in the voice channel, optionally can be provided time of muting is seconds - !mute 10
* !send (@users) (message) - sends message in private channel to mentioned users
* !sendAll (message) - sends message in private channel to all users in voice channel
* !unban (user) - unbans user
* !unmute (@users) - unmutes mentioned users
* !unmuteAll - unmutes all users in voice channel

#### Contacts
If you got any problems please contact me:
* [telegram](https://t.me/max1my)
* [email](maxy.chechetkin@gmail.com)
