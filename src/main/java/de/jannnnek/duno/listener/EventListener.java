package de.jannnnek.duno.listener;

import de.jannnnek.duno.loot.MoneyBundles;
import de.jannnnek.duno.player.FishingPlayer;
import de.jannnnek.duno.region.Region;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.hanging.HangingBreakByEntityEvent;
import org.bukkit.event.hanging.HangingPlaceEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;

import java.nio.channels.AcceptPendingException;

public class EventListener implements Listener {

    @EventHandler
    public void onPing(ServerListPingEvent e) {
        e.setMaxPlayers(Bukkit.getOnlinePlayers().size()+1);
        e.setMotd("§7[§bPre Alpha§7] §2Duno§r - §6Fishing §7[§a1.20+§7]§r\n§7           Discord: §ddc.dunomc.de");
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        Player p = e.getPlayer();
        String format = p.getName() + " §8: §7" + e.getMessage();
        p.sendMessage(format);
        for (Player all : Bukkit.getOnlinePlayers().stream().filter(_player -> !_player.equals(p)).toList()) {
            all.sendMessage(format);
        }
    }

    @EventHandler
    public void onPlayerDrop(PlayerDropItemEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemFramePlace(HangingPlaceEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onItemFrame(HangingBreakByEntityEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onPlayerHunger(FoodLevelChangeEvent e) {
        e.setFoodLevel(20);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            e.setCancelled(true);
            if (e.getCause().equals(EntityDamageEvent.DamageCause.VOID)){
                //TODO: Teleport
                //e.getEntity().teleport(Region.LOBBY.getSpawnLocation());
            }
        }
    }

//    @EventHandler
//    public void onThrow(ProjectileLaunchEvent e) {
//        if (!(e.getEntity() instanceof Firework)) {
//            e.setCancelled(true);
//        }
//    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getClickedInventory() == null) {
            e.setCancelled(true);
        }
        if (e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
            if (e.getRawSlot() == 5) {
                e.setCancelled(true);
            } else if (e.getRawSlot() == 36) {
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        Action action = e.getAction();
        if (action.equals(Action.RIGHT_CLICK_BLOCK) || action.equals(Action.RIGHT_CLICK_AIR)) {
            ItemStack item = player.getInventory().getItemInMainHand();
            if (item.getType().equals(Material.IRON_NUGGET)) {
                for (MoneyBundles moneyBundles : MoneyBundles.values()) {
                    if (item.getItemMeta().getCustomModelData() == moneyBundles.getCustomModelData()) {
                        FishingPlayer.players.get(player.getUniqueId()).addCoins(moneyBundles.getWorth());
                        player.getScoreboard().getTeam("coins").setSuffix("§6" + FishingPlayer.players.get(player.getUniqueId()).getCoins());
                        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                        player.sendMessage("§aDir wurden " + moneyBundles.getWorth() + " Coins zugeschrieben.");
                        item.setAmount(item.getAmount()-1);
                        break;
                    }
                }
            }
        }
    }

    @EventHandler
    public void onLeavesDecay(LeavesDecayEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void setDurability(PlayerItemDamageEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        for (Region region1 : Region.values()) {
            if (e.getPlayer().getWorld().getName().equals(region1.getStringWorld())) {
                e.getPlayer().getScoreboard().getTeam("region").setSuffix(region1.getName());
            }
        }
    }

}
