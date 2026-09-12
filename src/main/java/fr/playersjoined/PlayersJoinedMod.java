package fr.playersjoined;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.Mod;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Mod(PlayersJoinedMod.MOD_ID)
public final class PlayersJoinedMod {
    public static final String MOD_ID = "playersjoined";
    private static final int MAX_PLAYERS = 10_000;
    private static final String SAVE_FILE = "players_joined.txt";

    private static ServerBossEvent bossBar;
    private static Set<UUID> joinedPlayers = new HashSet<>();
    private static Path savePath;

    public PlayersJoinedMod() {
        PlayerEvent.PlayerLoggedInEvent.BUS.addListener(PlayersJoinedMod::onPlayerLogin);
        PlayerEvent.PlayerLoggedOutEvent.BUS.addListener(PlayersJoinedMod::onPlayerLogout);
    }

    private static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!(event.getEntity() instanceof ServerPlayer player)) return;
        MinecraftServer server = player.level().getServer();
        if (server == null) return;

        ensureLoaded(server);
        if (joinedPlayers.add(player.getUUID())) save();
        ensureBossBar();
        bossBar.addPlayer(player);
        updateBossBar();
    }

    private static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.getEntity() instanceof ServerPlayer player && bossBar != null) {
            bossBar.removePlayer(player);
        }
    }

    private static void ensureLoaded(MinecraftServer server) {
        if (savePath != null) return;
        savePath = server.getServerDirectory().resolve("config").resolve(SAVE_FILE);
        try {
            Files.createDirectories(savePath.getParent());
            if (Files.exists(savePath)) {
                for (String line : Files.readAllLines(savePath, StandardCharsets.UTF_8)) {
                    try { joinedPlayers.add(UUID.fromString(line.trim())); }
                    catch (IllegalArgumentException ignored) { }
                }
            }
        } catch (IOException e) {
            System.err.println("Unable to load players_joined.txt: " + e.getMessage());
        }
    }

    private static void save() {
        if (savePath == null) return;
        try {
            Files.write(savePath, joinedPlayers.stream().map(UUID::toString).sorted().toList(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Unable to save players_joined.txt: " + e.getMessage());
        }
    }

    private static void ensureBossBar() {
        if (bossBar == null) {
            bossBar = new ServerBossEvent(
                    UUID.randomUUID(),
                    Component.literal("Players Joined: 0/" + MAX_PLAYERS),
                    ServerBossEvent.BossBarColor.WHITE,
                    ServerBossEvent.BossBarOverlay.PROGRESS
            );
            bossBar.setVisible(true);
        }
    }

    private static void updateBossBar() {
        if (bossBar == null) return;
        int count = Math.min(joinedPlayers.size(), MAX_PLAYERS);
        bossBar.setName(Component.literal("Players Joined: " + count + "/" + MAX_PLAYERS));
        bossBar.setProgress((float) count / MAX_PLAYERS);
    }
}