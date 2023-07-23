package de.jannnnek.duno.npc.trait;

import de.jannnnek.duno.player.FishingPlayer;
import de.jannnnek.duno.region.Region;
import de.jannnnek.duno.rewards.Hats;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.api.trait.Trait;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Collections;

public class CosmeticTrait extends Trait implements Listener {

    public CosmeticTrait() {
        super("cosmetictrait");
    }

    @EventHandler
    public void rightClick(NPCRightClickEvent e) {
        if (e.getNPC().getName().equals("Cosmetics")) {
            e.getClicker().openInventory(getCosmeticsInv(e.getClicker()));
        }
    }

    @EventHandler
    public void onInteract(InventoryClickEvent e) {
        if (!(e.getWhoClicked() instanceof Player player)) {
            return;
        }
        InventoryView inventoryView = e.getView();
        String inventoryTitle = inventoryView.getTitle();
        Inventory inventory = inventoryView.getTopInventory();
        //TODO: name und items
        if (!inventoryTitle.equals("§fCosmetics")) {
            return;
        }
        e.setCancelled(true);
        if (inventory.getItem(e.getRawSlot()).getType().equals(Material.POPPED_CHORUS_FRUIT)) {
            return;
        }
        if (player.hasCooldown(Material.REDSTONE_ORE)) {
            return;
        }
        switch (e.getRawSlot()) {
            case 10:
                setCosmetic(player, 1);
                break;
            case 12:
                setCosmetic(player, 2);
                break;
            case 14:
                setCosmetic(player, 3);
                break;
            case 16:
                setCosmetic(player, 4);
                break;
            case 20:
                setCosmetic(player, 5);
                break;
            case 22:
                setCosmetic(player, 6);
                break;
            case 24:
                setCosmetic(player, 7);
                break;
            case 28:
                setCosmetic(player, 8);
                break;
            case 30:
                setCosmetic(player, 9);
                break;
            case 32:
                setCosmetic(player, 10);
                break;
            case 34:
                setCosmetic(player, 11);
                break;
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

    //TODO: namen und items
    private Inventory getCosmeticsInv(Player player) {
        FishingPlayer fishingPlayer = FishingPlayer.players.get(player.getUniqueId());
        Inventory inv = Bukkit.createInventory(null, 45, "§fCosmetics");
        Integer[] slots = {10, 12, 14, 16, 20, 22, 24, 28, 30, 32, 34};
        for (Hats hats : Hats.values()) {
            if (fishingPlayer.collectionIsFull(hats.getRegion())) {
                if (player.getInventory().getHelmet() != null) {
                    if (player.getInventory().getHelmet().equals(createItemStack(Material. PURPLE_DYE, 1, hats.getName(), "", hats.getCustomModelData()))) {
                        inv.setItem(slots[hats.getCustomModelData() - 1], createItemStack(Material.PURPLE_DYE, 1, hats.getName(), "\n LINKSKLICK UNICODE zum ablegen", hats.getCustomModelData()));
                    }
                }
                 else {
                    inv.setItem(slots[hats.getCustomModelData() - 1], createItemStack(Material.PURPLE_DYE, 1, hats.getName(), "\n LINKSKLICK UNICODE zum ausrüsten", hats.getCustomModelData()));
                }
            } else {
                inv.setItem(slots[hats.getCustomModelData()-1], createItemStack(Material.POPPED_CHORUS_FRUIT, 1, hats.getName(), "\n Vervollständige die " + hats.getRegion().getName() + " Sammlung um diesen Hut freizuschalten", 3));
            }
        }
        return inv;
    }

    private void setCosmetic(Player player, int customModelData) {
        FishingPlayer fishingPlayer = FishingPlayer.players.get(player.getUniqueId());
        if (player.getInventory().getHelmet() != null) {
            if (player.getInventory().getHelmet().getItemMeta().getCustomModelData() == customModelData) {
                fishingPlayer.setEquipedHat(0);
                player.getInventory().setHelmet(new ItemStack(Material.AIR));
        }
        } else {
            fishingPlayer.setEquipedHat(customModelData);
            Hats hat = Hats.getHat(customModelData);
            player.getInventory().setHelmet(createItemStack(Material. PURPLE_DYE, 1, hat.getName(), "", hat.getCustomModelData()));
        }
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
        player.closeInventory();
        player.setCooldown(Material.REDSTONE_ORE, 5);
    }
}
