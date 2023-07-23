package de.jannnnek.duno.npc.trait;

import de.jannnnek.duno.Duno;
import de.jannnnek.duno.loot.Fish;
import de.jannnnek.duno.loot.Scrap;
import de.jannnnek.duno.player.FishingPlayer;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class HaendlerTrait extends Trait implements Listener {

    public HaendlerTrait() {
        super("haendlertrait");
    }

    @EventHandler
    public void rightClick(NPCRightClickEvent e) {
        if (e.getNPC().getName().equals("Händler")) {
            //TODO: name und items
            Inventory inv = Bukkit.createInventory(null, 54, "§fHandler");
            for (int i=45; i <= 53; i++) {
                if (i == 49) {continue;}
                inv.setItem(i, createItemStack(Material.POPPED_CHORUS_FRUIT, 1, "Ich bin unsichtbar", "", 1));
            }
            inv.setItem(49, createItemStack(Material.POPPED_CHORUS_FRUIT, 1, "Verkaufen", "§7Coins: §6" + calculateWorth(e.getClicker(), "§fPlaceholder"), 2));
            e.getClicker().openInventory(inv);
        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        InventoryView inventoryView = e.getView();
        String inventoryTitle = inventoryView.getTitle();
        Inventory inventory = inventoryView.getTopInventory();
        //TODO: name und items
        if (!inventoryTitle.equals("§fHandler")) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        if (e.getSlot() <= 44 && e.getSlot() >= 0) {
            ItemStack clickedItem = e.getCurrentItem();
            if (clickedItem == null || (clickedItem.getType() != Material.RED_DYE && clickedItem.getType() != Material.WHITE_DYE)) {
                e.setCancelled(true);
            }
            Bukkit.getScheduler().runTask(Duno.getInstance(), new Runnable() {
                @Override
                public void run() {
                    ItemStack sellItem = createItemStack(Material.POPPED_CHORUS_FRUIT, 1, "Verkaufen", "§7Coins: §6" + calculateWorth(player, inventoryTitle), 2);
                    inventory.setItem(49, sellItem);
                }
            });
        } else if (e.getSlot() == 49) {
            e.setCancelled(true);
            if (calculateWorth(player, inventoryTitle) == 0) {
                e.setCancelled(true);
                return;
            }
            FishingPlayer.players.get(player.getUniqueId()).addCoins(calculateWorth(player, inventoryTitle));
            player.getScoreboard().getTeam("coins").setSuffix("§6" + FishingPlayer.players.get(player.getUniqueId()).getCoins());
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
            inventory.remove(Material.RED_DYE);
            inventory.remove(Material.WHITE_DYE);
            ItemStack sellItem = createItemStack(Material.POPPED_CHORUS_FRUIT, 1, "Verkaufen", "§7Coins: §6" + calculateWorth(player, inventoryTitle), 2);
            inventory.setItem(49, sellItem);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer();
        InventoryView inventoryView = e.getView();
        String inventoryTitle = inventoryView.getTitle();
        // TODO: name und items
        if (inventoryTitle.equals("§fHandler")) {
            boolean empty = true;
            for (int i=0; i <= 44; i++) {
                if (inventoryView.getItem(i) != null) {
                    empty = false;
                    break;
                }
            }
            if (!empty) {
                FishingPlayer.players.get(player.getUniqueId()).addCoins(calculateWorth(player, inventoryTitle));
                player.getScoreboard().getTeam("coins").setSuffix("§6" + FishingPlayer.players.get(player.getUniqueId()).getCoins());
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                inventoryView.getTopInventory().remove(Material.RED_DYE);
                inventoryView.getTopInventory().remove(Material.WHITE_DYE);
            }
        }
    }

    public static ItemStack createItemStack(Material material, int amount, String displayname, String lore, int customModelData) {
        ItemStack itemStack = new ItemStack(material,amount);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayname);
        itemMeta.setCustomModelData(customModelData);
        itemMeta.setLore(Collections.singletonList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;

    }

    private int calculateWorth(Player player, String inventoryTitle) {
        int worth = 0;
        InventoryView inventoryView = player.getOpenInventory();
        if (!inventoryView.getTitle().equals(inventoryTitle)) {
            return 0;
        }
        for (int i=0; i <= 44; i++) {
            ItemStack item = inventoryView.getItem(i);
            if (item == null) {
                continue;
            }
            int amount = item.getAmount();
            if (item.getType().equals(Material.RED_DYE)) {
                for (Scrap scrap : Scrap.values()) {
                    if (scrap.getCustomModelData() == item.getItemMeta().getCustomModelData()) {
                        worth += (scrap.getWorth() * amount);
                    }
                }
            } else if (item.getType().equals(Material.WHITE_DYE)) {
                for (Fish fish : Fish.values()) {
                    if (fish.getCustomModelData() == item.getItemMeta().getCustomModelData()) {
                        worth += (fish.getWorth() * amount);
                    }
                }
            }
        }
        return worth;
    }

}
