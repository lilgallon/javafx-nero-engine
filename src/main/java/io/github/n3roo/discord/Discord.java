package io.github.n3roo.discord;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import io.github.n3roo.events.EventManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Discord {

    // Lo4j2 logger: its properties are found in "log4j2.properties" in resources/ folder.
    private static final Logger LOGGER = LogManager.getLogger(Discord.class.getName());

    // nero-engine app id
    private final String APP_ID = "576397580620005377";

    // The discordRPC lib
    private DiscordRPC lib = DiscordRPC.INSTANCE;

    public Discord(){
        LOGGER.info("DISCORD: handlers creation");
        DiscordEventHandlers handlers = new DiscordEventHandlers();
        handlers.ready = (var) -> handleReady();
        handlers.errored = (var, var2) -> handleError();
        handlers.disconnected = (var1, var2) -> handleDisconnected();
        handlers.joinGame = (var) -> handleJoinGame();
        handlers.spectateGame = (var) -> handleSpectateGame();
        handlers.joinRequest = (var) -> handleJoinRequest();
        lib.Discord_Initialize(APP_ID, handlers, true, "");

        // TODO: is it better to do it in World.update()?
        LOGGER.info("DISCORD: Creating a callback handler thread");
        Thread callbackHandler = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {}
            }
        }, "NeroEngine-DiscordRPC-Callback-Handler");
        callbackHandler.start();

        LOGGER.info("DISCORD: Creating shutdown events");
        EventManager.addOnEngineClosingListener(callbackHandler::interrupt);
        EventManager.addOnEngineClosingListener(() -> lib.Discord_Shutdown());

        LOGGER.info("DISCORD: Connection...");
    }

    /**
     * !!! Here are some changes that are not in the official API:
     * - If you want to ignore a String parameter, use "",
     * - If you want to ignore a long/int parameter, use -1,
     * The doc is from discordapp API: https://discordapp.com/developers/docs/rich-presence/how-to
     * The core of Discord's Rich Presence SDK is the Discord_UpdatePresence() function. This is what sends your game
     * data up to Discord to be seen and used by others. You should call Discord_UpdatePresence() any time something
     * important in the presence payload changes.
     * Discord_UpdatePresence() has a rate limit of one update per 15 seconds. Developers do not need to do anything to
     * handle this rate limit. The SDK will queue up any presence updates sent in that window and send the newest one
     * once the client is free to do so. If you are sending presence updates very frequently and wondering why you don't
     * see your Discord presence changing, that's why!
     * @param state the user's current party status,
     * @param details what the player is currently doing,
     * @param startTimestamp epoch seconds for game start - including will show time as "elapsed",
     * @param endTimestamp epoch seconds for game end - including will show time as "remaining",
     * @param largeImageKey name of the uploaded image for the large profile artwork,
     * @param largeImageText tooltip for the largeImageKey,
     * @param smallImageKey name of the uploaded image for the small profile artwork,
     * @param smallImageText tootltip for the smallImageKey,
     * @param partyId id of the player's party, lobby, or group,
     * @param partySize current size of the player's party, lobby, or group,
     * @param partyMax maximum size of the player's party, lobby, or group,
     * @param spectateSecret unique hashed string for Spectate button,
     * @param joinSecret unique hashed string for chat invitations and Ask to Join.
     */
    public void updatePresence(String state, String details, long startTimestamp, long endTimestamp,
                               String largeImageKey, String largeImageText, String smallImageKey, String smallImageText,
                               String partyId, int partySize, int partyMax, String spectateSecret, String joinSecret){
        DiscordRichPresence discordRichPresence = new DiscordRichPresence();

        discordRichPresence.state = state.equals("") ? null : state;
        discordRichPresence.details = details.equals("") ? null : details;
        if(startTimestamp != -1) discordRichPresence.startTimestamp = startTimestamp;
        if(endTimestamp != -1) discordRichPresence.endTimestamp = endTimestamp;
        discordRichPresence.largeImageKey = largeImageKey.equals("") ? null : largeImageKey;
        discordRichPresence.largeImageText = largeImageText.equals("") ? null : largeImageText;
        discordRichPresence.smallImageKey = smallImageKey.equals("") ? null : smallImageKey;
        discordRichPresence.smallImageText = smallImageText.equals("") ? null : smallImageText;
        discordRichPresence.partyId = partyId.equals("") ? null : partyId;
        if(partySize != -1) discordRichPresence.partySize = partySize;
        if(partyMax != -1) discordRichPresence.partyMax = partyMax;
        discordRichPresence.spectateSecret = spectateSecret.equals("") ? null : spectateSecret;
        discordRichPresence.joinSecret = joinSecret.equals("") ? null : joinSecret;

        lib.Discord_UpdatePresence(discordRichPresence);
    }

    /**
     * Here is the fantastic discord documentation: https://discordapp.com/developers/docs/rich-presence/how-to
     * Alternatively, you can use updatePresence(state, ..., joinSecret).
     * @param discordRichPresence presence information.
     */
    public void updatePresence(DiscordRichPresence discordRichPresence){
        lib.Discord_UpdatePresence(discordRichPresence);
    }

    public void clearPresence(){
        lib.Discord_ClearPresence();
    }

    // Override these methods to handle events

    protected void handleReady(){ LOGGER.info("DISCORD: ready"); }
    protected void handleError(){ LOGGER.info("DISCORD: error"); }
    protected void handleDisconnected(){ LOGGER.info("DISCORD: disconnected"); }
    protected void handleJoinGame(){}
    protected void handleSpectateGame(){}
    protected void handleJoinRequest(){}
}
