package de.jannnnek.duno.listener;

import de.jannnnek.duno.Duno;
import de.jannnnek.duno.player.FishingPlayer;
import de.jannnnek.duno.region.Region;
import de.jannnnek.duno.rewards.Title;
import de.jannnnek.duno.rods.Rod;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class ConnectionListener implements Listener {

    @EventHandler
    public void onLogin(PlayerLoginEvent e) {
        if(Duno.serverStarting) e.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§cDer Server startet gerade...");
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        setFishingPlayer(player);
        setScoreboard(e);
        player.setHealth(20);
        player.setFoodLevel(20);
        player.setGameMode(GameMode.SURVIVAL);
        player.getInventory().setItem(0, Rod.STARTER.getRod());
        e.setJoinMessage(null);
        player.setResourcePack("https://download.mc-packs.net/pack/1a0c3312144af7a580e65142b06da77aeda8d2f7.zip", "1a0c3312144af7a580e65142b06da77aeda8d2f7");
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage("§4« §7" + e.getPlayer().getName());
        if (FishingPlayer.players.containsKey(e.getPlayer().getUniqueId())) {
            FishingPlayer.players.get(e.getPlayer().getUniqueId()).save();
        }
        e.getPlayer().teleport(Duno.getRegionSpawnPoint(Region.LOBBY));
    }

//    TODO: wieder anmachen
//    @EventHandler
//    public void onResourcePack(PlayerResourcePackStatusEvent e) {
//        Player p = e.getPlayer();
//        if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED)) {
//            Bukkit.broadcastMessage("§f\ue001 §2» §7" + p.getName());
//        } else if(e.getStatus().equals(PlayerResourcePackStatusEvent.Status.DECLINED) || e.getStatus().equals(PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD)) {
//            p.kickPlayer("Du musst das Ressourcenpaket aktivieren, um auf Duno spielen zu können.\n\nGehe dafür auf Server bearbeiten ➜ Server-Ressourcenpakete ➜ Aktiviert");
//        }
//    }

    private void setScoreboard(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        Scoreboard sb = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = sb.registerNewObjective("test", "dummy");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.setDisplayName("§2§lDuno");

        Score discord = obj.getScore("§a     dc.discord.com");
        discord.setScore(1);

        Score space = obj.getScore(" ");
        space.setScore(2);

        Team catchedFish = sb.registerNewTeam("catchedFish");
        catchedFish.addEntry("§7");
        catchedFish.setPrefix("§7Gefangene Fische: ");
        catchedFish.setSuffix("§6" + FishingPlayer.players.get(player.getUniqueId()).getCatchedFish());
        obj.getScore("§7").setScore(3);

        Score space2 = obj.getScore("  ");
        space2.setScore(4);

        Team coins = sb.registerNewTeam("coins");
        coins.addEntry("§4§7");
        coins.setPrefix("§7Coins: ");
        coins.setSuffix("§6" + Math.round(FishingPlayer.players.get(player.getUniqueId()).getCoins()));
        obj.getScore("§4§7").setScore(5);

        Score space3 = obj.getScore("   ");
        space3.setScore(6);

        Team region = sb.registerNewTeam("region");
        region.addEntry("§5§7");
        region.setPrefix("§7Region: ");
        for (Region region1 : Region.values()) {
            if (player.getWorld().getName().equals(region1.getStringWorld())) {
                region.setSuffix(region1.getName());
            }
        }
        obj.getScore("§5§7").setScore(7);

        Score space4 = obj.getScore("    ");
        space4.setScore(8);


        player.setScoreboard(sb);
    }

    public void setFishingPlayer(Player player) {
        UUID uuid = player.getUniqueId();
        FishingPlayer user = FishingPlayer.players.containsKey(uuid) ? FishingPlayer.players.get(uuid) : new FishingPlayer(uuid);
    }

}
