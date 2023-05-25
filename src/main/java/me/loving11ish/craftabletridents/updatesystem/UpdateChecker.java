package me.loving11ish.craftabletridents.updatesystem;

import com.tcoded.folialib.FoliaLib;
import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.util.Consumer;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

public class UpdateChecker {

    private int resourceId;
    private FoliaLib foliaLib = CraftableTridents.getFoliaLib();
    FileConfiguration messagesConfig = CraftableTridents.getPlugin().messagesFileManager.getMessagesConfig();
    Logger logger = CraftableTridents.getPlugin().getLogger();

    public UpdateChecker(int resourceId) {
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        foliaLib.getImpl().runAsync(() -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                logger.warning(ColorUtils.translateColorCodes(messagesConfig.getString("update-check-failure") + exception.getMessage()));
            }
        });
    }
}
