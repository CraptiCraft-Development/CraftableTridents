package me.loving11ish.craftabletridents.UpdateSystem;

import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.Utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

public class UpdateChecker {

    private Plugin plugin;
    private int resourceId;
    Logger logger = CraftableTridents.getPlugin().getLogger();

    public UpdateChecker(Plugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                logger.warning(ColorUtils.translateColorCodes(CraftableTridents.getPlugin().getConfig().getString("Update-check-failure") + exception.getMessage()));
            }
        });
    }
}
