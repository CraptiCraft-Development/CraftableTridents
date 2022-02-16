package me.loving11ish.craftabletridents.files;

import me.loving11ish.craftabletridents.CraftableTridents;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Logger;

public class MessagesFileManager {

    private CraftableTridents plugin;
    private FileConfiguration dataConfig = null;
    private File configFile = null;

    Logger logger = CraftableTridents.getPlugin().getLogger();

    public void MessagesFileManager(CraftableTridents plugin){
        this.plugin = plugin;
        saveDefaultMessagesConfig();
    }

    public void reloadMessagesConfig(){
        if (this.configFile == null){
            this.configFile = new File(plugin.getDataFolder(), "messages.yml");
        }
        this.dataConfig = YamlConfiguration.loadConfiguration(this.configFile);
        InputStream defaultStream = this.plugin.getResource("messages.yml");
        if (defaultStream != null){
            YamlConfiguration defaultConfig = YamlConfiguration.loadConfiguration(new InputStreamReader(defaultStream));
            this.dataConfig.setDefaults(defaultConfig);
        }
    }

    public FileConfiguration getMessagesConfig(){
        if (this.dataConfig == null){
            this.reloadMessagesConfig();
        }
        return this.dataConfig;
    }

    public void saveMessagesConfig() {
        if (this.dataConfig == null||this.configFile == null){
            return;
        }
        try {
            this.getMessagesConfig().save(this.configFile);
        }catch (IOException e){
            logger.severe(ColorUtils.translateColorCodes("&f[&6Craftable&eTridents&f] - &4Could not save messages.yml"));
            logger.severe(ColorUtils.translateColorCodes("&f[&6Craftable&eTridents&f] - &4Check the below message for the reasons!"));
            e.printStackTrace();
        }
    }

    public void saveDefaultMessagesConfig(){
        if (this.configFile == null){
            this.configFile = new File(plugin.getDataFolder(), "messages.yml");
        }
        if (!this.configFile.exists()){
            this.plugin.saveResource("messages.yml", false);
        }
    }
}
