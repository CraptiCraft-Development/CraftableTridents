package me.loving11ish.craftabletridents.events;

import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

public class ItemCraftListener implements Listener {

    FileConfiguration messagesConfig = CraftableTridents.getPlugin().messagesFileManager.getMessagesConfig();

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onTridentItemCraft(CraftItemEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
           return;
        }
        if (!(event.getRecipe().getResult().equals(CraftableTridents.getPlugin().getTridentItem()))){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(player.hasPermission("ct.craft.trident")||player.hasPermission("ct.craft.*")
                ||player.hasPermission("ct.craft")||player.hasPermission("ct.*")||player.isOp())){
            event.setCancelled(true);
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("trident-no-permission")));
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onOPTridentItemCraft(CraftItemEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        if (!(event.getRecipe().getResult().equals(CraftableTridents.getPlugin().getOpTridentItem()))){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(player.hasPermission("ct.craft.op-trident")||player.hasPermission("ct.craft.*")
                ||player.hasPermission("ct.craft")||player.hasPermission("ct.*")||player.isOp())){
            event.setCancelled(true);
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("op-trident-no-permission")));
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onElytraItemCraft(CraftItemEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        if (!(event.getRecipe().getResult().equals(CraftableTridents.getPlugin().getElytraItem()))){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(player.hasPermission("ct.craft.elytra")||player.hasPermission("ct.craft.*")
                ||player.hasPermission("ct.craft")||player.hasPermission("ct.*")||player.isOp())){
            event.setCancelled(true);
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("elytra-no-permission")));
        }
    }

    @EventHandler (priority = EventPriority.HIGHEST)
    public void onGodAppleItemCraft(CraftItemEvent event){
        if (!(event.getWhoClicked() instanceof Player)){
            return;
        }
        if (!(event.getRecipe().getResult().equals(CraftableTridents.getPlugin().getEnchantedGoldenAppleItem()))){
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (!(player.hasPermission("ct.craft.godapple")||player.hasPermission("ct.craft.*")
                ||player.hasPermission("ct.craft")||player.hasPermission("ct.*")||player.isOp())){
            event.setCancelled(true);
            player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("god-apple-no-permission")));
        }
    }
}
