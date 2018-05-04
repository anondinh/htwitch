# HTwitch

This is an independent project not run by Twitch!

Feel free to join the HTwitch [Discord](https://discord.gg/CNk6aKp) if you have any questions or suggestions

***

## HTwitch made easy

```java
import org.dinhware.bot.HTwitchBot;
import org.dinhware.commands.Command;
import org.dinhware.bot.adapter.core.MessageAdapter;
import org.dinhware.bot.event.BitEvent;
import org.dinhware.bot.event.MessageEvent;
import org.dinhware.bot.objects.Capability;
import org.dinhware.bot.objects.Channel;

public class Test {

    public static void main(String[] args) {
        /*
         * Initialize a new HTwitchBot instance
         *
         * @param String name of the bot
         * @param String oauth of the bot, with or without 'oauth:' in-front
         */
        HTwitchBot bot = new HTwitchBot("bot-nickname", "bot-oauth");

        /*
         * Request all Capabilities
         * simplified to request all Capabilities bot.requestCapabilities(Capability.values());
         *
         * @param Capability[] types of Capabilities to request
         */
        bot.requestCapabilities(Capability.COMMANDS, Capability.MEMBERSHIP, Capability.TAGS);

         /*
         * Toggle verbose mode on
         * Print anything read by the reader
         * 
         * @param boolean
         */
        bot.setVerbose(true);

        /*
         * Toggle error-verbose mode on
         * Print any error thrown by the internal-api-code
         * This includes any Listener or Command you add
         * 
         * @param boolean
         */
        bot.setPrintError(true);

        /*
         * Create a new MessageListener instance
         *
         * @param String prefix for the command
         */
        MessageAdapter listener = new MessageAdapter("!") {
            @Override
            protected void onMessage(MessageEvent event) {
                /*
                 * Reply to any Event
                 *
                 * @param Object message
                 */
                event.respond("Hey there!");
            }

            @Override
            protected void onBitDonation(BitEvent event) {

            }
        };

        /*
         * Adding a Command to the MessageAdapter
         *
         * @param String trigger
         * @param ChatCommand instance
         */
        listener.addCommand("test", new ChatCommand() {
                    @Override
                    protected void execute(MessageEvent event) {
                        event.respond("test was used!");
                    }

                    @Override
                    public long getCooldown() {
                        return 5000;
                    }
                });

        /*
         * Adding the Listener to our Bot
         *
         * @param Listener instance
         */
        bot.addListener(listener);


        /*
         * Join a channel
         *
         * @param String channel-name
         */
        Channel channel = bot.join("channel-name");

        /*
         * Send a message to a channel
         *
         * @param Channel
         * @param String message
         */
        bot.send(channel, "Hey there I just joined this channel!");
    }

}

```

***
Please support me by sharing this library and hitting that star button. Thank you.
