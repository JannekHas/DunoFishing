package de.jannnnek.duno.npc.trait;

import de.jannnnek.duno.Duno;
import de.jannnnek.duno.loot.Fish;
import de.jannnnek.duno.player.FishingPlayer;
import de.jannnnek.duno.region.Region;
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

public class CollectionTrait extends Trait implements Listener {

    public CollectionTrait() {
        super("collectiontrait");
    }

    @EventHandler
    public void rightClick(NPCRightClickEvent e) {
        if (e.getNPC().getName().equals("Sammlungen")) {
            e.getClicker().openInventory(getCollectionInv());
        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) e.getWhoClicked();
        InventoryView inventoryView = e.getView();
        String inventoryTitle = inventoryView.getTitle();
        Inventory inventory = inventoryView.getTopInventory();
        int slot = e.getRawSlot();
        //TODO: name und items
        if (inventoryTitle.equals("§fSammlung")) {
            e.setCancelled(true);
            switch (slot) {
                case 10 -> player.openInventory(getRegionInv(player, Region.LOBBY));
                case 12 -> player.openInventory(getRegionInv(player, Region.TUEMPEL));
                case 14 -> player.openInventory(getRegionInv(player, Region.BACH));
                case 16 -> player.openInventory(getRegionInv(player, Region.EISSCHOLLE));
                case 20 -> player.openInventory(getRegionInv(player, Region.FLUSS));
                case 22 -> player.openInventory(getRegionInv(player, Region.BERGSEE));
                case 24 -> player.openInventory(getRegionInv(player, Region.SUMPF));
                case 28 -> player.openInventory(getRegionInv(player, Region.SCHIFFKUTTER));
                case 30 -> player.openInventory(getRegionInv(player, Region.TROPISCHE_INSEL));
                case 32 -> player.openInventory(getRegionInv(player, Region.MANGROVEN));
                case 34 -> player.openInventory(getRegionInv(player, Region.HOEHLENSEE));
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

    //TODO: name und items

    private Inventory getCollectionInv() {
        Inventory inv = Bukkit.createInventory(null, 45, "§fSammlung");
        Integer[] slots = {10, 12, 14, 16, 20, 22, 24, 28, 30, 32, 34};
        for (Region region : Region.values()) {
            inv.setItem(slots[region.getIndex()], createItemStack(Material.GREEN_DYE, 1, region.getName(), "\n LINKSKLICK UNICODE um die " + region.getName() + " Sammlung anzuschauen", region.getIndex()));
        }
        return inv;
    }

    @EventHandler
    public void onRegionInv(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) {
            e.setCancelled(true);
            return;
        }
        for (Region region : Region.values()) {
            if (e.getView().getTitle().equals(region.getCollectionInvName())) {
                e.setCancelled(true);
            }
        }
    }

    private Inventory getRegionInv(Player player, Region region) {
        Bukkit.broadcastMessage(region.getName());
        FishingPlayer fishingPlayer = FishingPlayer.players.get(player.getUniqueId());
        Inventory inv = Bukkit.createInventory(null, 54, region.getCollectionInvName());
        Integer[] block = {0, 1, 2, 3, 4, 5, 6, 7, 8};
        for (int i : block) {
            inv.setItem(i, createItemStack(Material.POPPED_CHORUS_FRUIT, 1, "Ich bin unsichtbar", "", 1));
        }
        for (Fish fish : region.getFish()) {
            if (fishingPlayer.getRecord(fish.getName()) == 0.0) {
                inv.addItem(createItemStack(Material.POPPED_CHORUS_FRUIT, 1, fish.getName(), "", 2));
            } else {
                inv.addItem(fish.getFishRecord(player));
            }
        }
        return inv;
    }


}
