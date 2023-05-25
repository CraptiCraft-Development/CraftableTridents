package me.loving11ish.craftabletridents.updatesystem;

import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {

    CraftableTridents plugin;
    FileConfiguration messagesConfig = CraftableTridents.getPlugin().messagesFileManager.getMessagesConfig();

    public JoinEvent(CraftableTridents plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("ct.update")) {
            new UpdateChecker(95032).getVersion(version -> {
                try {
                    if (!(plugin.getDescription().getVersion().equalsIgnoreCase(version))) {
                        player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("update-1")));
                        player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("update-2")));
                        player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("update-3")));
                    }
                }catch (NullPointerException e){
                    player.sendMessage(ColorUtils.translateColorCodes(messagesConfig.getString("update-check-failure")));
                }
            });
        }
    }
}
