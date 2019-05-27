package io.github.n3roo.discord;

import club.minnced.discord.rpc.DiscordRichPresence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DiscordTest {

    @Test
    @Order(1)
    void getInstance1() {
        assertThrows(AssertionError.class, Discord::getInstance);
    }

    @Test
    @Order(2)
    void init() {
        assertDoesNotThrow(() -> Discord.init("576397580620005377"));
    }

    @Test
    @Order(3)
    void getInstance2() {
        assertDoesNotThrow(Discord::getInstance);
    }

    @Test
    @Order(4)
    void updateAndGetPresence() {
        DiscordRichPresence discordRichPresence = Discord.getInstance().getPresence();
        assertNull(discordRichPresence.state);
        assertNull(discordRichPresence.details);
        assertEquals(0, discordRichPresence.startTimestamp);
        assertEquals(0, discordRichPresence.endTimestamp);
        assertNull(discordRichPresence.largeImageKey);
        assertNull(discordRichPresence.largeImageText);
        assertNull(discordRichPresence.smallImageKey);
        assertNull(discordRichPresence.smallImageText);
        assertNull(discordRichPresence.partyId);
        assertEquals(0, discordRichPresence.partySize);
        assertEquals(0, discordRichPresence.partyMax);
        assertNull(discordRichPresence.spectateSecret);
        assertNull(discordRichPresence.joinSecret);

        discordRichPresence.state = "state";
        discordRichPresence.details = "details";
        discordRichPresence.startTimestamp = 1;
        discordRichPresence.endTimestamp = 2;
        discordRichPresence.largeImageKey = "largeImageKey";
        discordRichPresence.largeImageText = "largeImageText";
        discordRichPresence.smallImageKey = "smallImageKey";
        discordRichPresence.smallImageText = "smallImageText";
        discordRichPresence.partyId = "partyId";
        discordRichPresence.partySize = 3;
        discordRichPresence.partyMax = 4;
        discordRichPresence.spectateSecret = "spectateSecret";
        discordRichPresence.joinSecret = "joinSecret";

        Discord.getInstance().updatePresence(discordRichPresence);
        DiscordRichPresence drp = Discord.getInstance().getPresence();

        assertEquals("state", drp.state);
        assertEquals("details", drp.details);
        assertEquals(1, drp.startTimestamp);
        assertEquals(2, drp.endTimestamp);
        assertEquals("largeImageKey", drp.largeImageKey);
        assertEquals("largeImageText", drp.largeImageText);
        assertEquals("smallImageKey", drp.smallImageKey);
        assertEquals("smallImageText", drp.smallImageText);
        assertEquals("partyId", drp.partyId);
        assertEquals(3, drp.partySize);
        assertEquals(4, drp.partyMax);
        assertEquals("spectateSecret", drp.spectateSecret);
        assertEquals("joinSecret", drp.joinSecret);

        Discord.getInstance().updatePresence(
                "state1", "details1", 5, 6,
                "largeImageKey1", "largeImageText1",
                "smallImageKey1", "smallImageText1",
                "partyId1", 7, 8,
                "spectateSecret1", "joinSecret1");
        drp = Discord.getInstance().getPresence();

        assertEquals("state1", drp.state);
        assertEquals("details1", drp.details);
        assertEquals(5, drp.startTimestamp);
        assertEquals(6, drp.endTimestamp);
        assertEquals("largeImageKey1", drp.largeImageKey);
        assertEquals("largeImageText1", drp.largeImageText);
        assertEquals("smallImageKey1", drp.smallImageKey);
        assertEquals("smallImageText1", drp.smallImageText);
        assertEquals("partyId1", drp.partyId);
        assertEquals(7, drp.partySize);
        assertEquals(8, drp.partyMax);
        assertEquals("spectateSecret1", drp.spectateSecret);
        assertEquals("joinSecret1", drp.joinSecret);

        Discord.getInstance().clearPresence();
        drp = Discord.getInstance().getPresence();

        assertNull(drp.state);
        assertNull(drp.details);
        assertEquals(0, drp.startTimestamp);
        assertEquals(0, drp.endTimestamp);
        assertNull(drp.largeImageKey);
        assertNull(drp.largeImageText);
        assertNull(drp.smallImageKey);
        assertNull(drp.smallImageText);
        assertNull(drp.partyId);
        assertEquals(0, drp.partySize);
        assertEquals(0, drp.partyMax);
        assertNull(drp.spectateSecret);
        assertNull(drp.joinSecret);

        assertNotEquals(drp, discordRichPresence);
    }

    @Order(6)
    @Test
    void events(){
        assertDoesNotThrow(() -> Discord.getInstance().handleReady());
        assertDoesNotThrow(() -> Discord.getInstance().handleError());
        assertDoesNotThrow(() -> Discord.getInstance().handleDisconnected());
        assertDoesNotThrow(() -> Discord.getInstance().handleJoinGame());
        assertDoesNotThrow(() -> Discord.getInstance().handleSpectateGame());
        assertDoesNotThrow(() -> Discord.getInstance().handleJoinRequest());
    }

    @Order(7)
    @Test
    void destroy(){
        assertDoesNotThrow(() -> Discord.getInstance().destroy());
    }
}