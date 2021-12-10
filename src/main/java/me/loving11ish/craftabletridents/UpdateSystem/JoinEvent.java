package me.loving11ish.craftabletridents.UpdateSystem;

import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.Utils.ColorUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    CraftableTridents plugin;

    public JoinEvent(CraftableTridents plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("ct.update")) {
            new UpdateChecker(plugin, 95032).getVersion(version -> {
                try {
                    if (!(plugin.getDescription().getVersion().equalsIgnoreCase(version))) {
                        player.sendMessage(ColorUtils.translateColorCodes(CraftableTridents.getPlugin().getConfig().getString("Update-1")));
                        player.sendMessage(ColorUtils.translateColorCodes(CraftableTridents.getPlugin().getConfig().getString("Update-2")));
                        player.sendMessage(ColorUtils.translateColorCodes(CraftableTridents.getPlugin().getConfig().getString("Update-3")));
                    }
                }catch (NullPointerException e){
                    player.sendMessage(ColorUtils.translateColorCodes(CraftableTridents.getPlugin().getConfig().getString("Update-check-failure")));
                }
            });
        }
    }
}
