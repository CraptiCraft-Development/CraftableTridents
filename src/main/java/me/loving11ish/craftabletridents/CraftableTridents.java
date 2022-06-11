package me.loving11ish.craftabletridents;

import me.loving11ish.craftabletridents.files.MessagesFileManager;
import me.loving11ish.craftabletridents.recipes.ElytraRecipe;
import me.loving11ish.craftabletridents.recipes.TridentRecipe;
import me.loving11ish.craftabletridents.updatesystem.JoinEvent;
import me.loving11ish.craftabletridents.updatesystem.UpdateChecker;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CraftableTridents extends JavaPlugin {

    public static CraftableTridents plugin;
    private PluginDescriptionFile pluginInfo = getDescription();
    private String pluginVersion = pluginInfo.getVersion();
    Logger logger = this.getLogger();

    public MessagesFileManager messagesFileManager;

    @Override
    public void onEnable() {
        //Plugin startup logic
        plugin = this;

        //Server version compatibility check
        if (!(Bukkit.getServer().getVersion().contains("1.13")||Bukkit.getServer().getVersion().contains("1.14")||
                Bukkit.getServer().getVersion().contains("1.15")||Bukkit.getServer().getVersion().contains("1.16")||
                Bukkit.getServer().getVersion().contains("1.17")||Bukkit.getServer().getVersion().contains("1.18")||
                Bukkit.getServer().getVersion().contains("1.19"))){
            logger.warning(ChatColor.RED + "-------------------------------------------");
            logger.warning(ChatColor.RED + "CraftableTridents - This plugin is only supported on the Minecraft versions listed below:");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.13.x");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.14.x");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.15.x");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.16.x");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.17.x");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.18.x");
            logger.warning(ChatColor.RED + "CraftableTridents - 1.19.x");
            logger.warning(ChatColor.RED + "CraftableTridents - Is now disabling!");
            logger.warning(ChatColor.RED + "-------------------------------------------");
            Bukkit.getPluginManager().disablePlugin(this);
        }else {
            logger.info(ChatColor.GREEN + "-------------------------------------------");
            logger.info(ChatColor.GREEN + "CraftableTridents - A supported Minecraft version has been detected");
            logger.info(ChatColor.GREEN + "CraftableTridents - Continuing plugin startup");
            logger.info(ChatColor.GREEN + "-------------------------------------------");
        }

        //Create & register config
        getConfig().options().copyDefaults();
        saveDefaultConfig();

        //Load messages.yml
        this.messagesFileManager = new MessagesFileManager();
        messagesFileManager.MessagesFileManager(this);

        //Register recipes
        TridentRecipe tridentRecipe = new TridentRecipe();
        ElytraRecipe elytraRecipe = new ElytraRecipe();
        tridentRecipe.unEnchantedRecipe();
        logger.info("-------------------------------------------");
        logger.info(ChatColor.AQUA + "CraftableTridents - Standard Trident Recipe Loaded!");
        if (getConfig().getBoolean("op-trident.enabled")){
            tridentRecipe.enchantedRecipe();
            logger.info(ChatColor.AQUA + "CraftableTridents - OP Trident Recipe Loaded!");
        }
        if (getConfig().getBoolean("elytra.enabled")){
            elytraRecipe.elytraRecipe();
            logger.info(ChatColor.AQUA + "CraftableTridents - Elytra Recipe Loaded!");
        }
        logger.info("-------------------------------------------");

        //Register events here
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);

        //Check for available updates
        new UpdateChecker(this, 95032).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                logger.info(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("no-update-1")));
                logger.info(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("no-update-2")));
                logger.info(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("no-update-3")));
            }else {
                logger.warning(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("update-1")));
                logger.warning(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("update-2")));
                logger.warning(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("update-3")));
            }
        });

        //Plugin startup message
        logger.info(ChatColor.AQUA + "CraftableTridents - Plugin By Loving11ish");
        logger.info(ChatColor.AQUA + "CraftableTridents - has been loaded successfully!");
        logger.info(ChatColor.AQUA + "CraftableTridents - Plugin Version " + pluginVersion);
        logger.info("-------------------------------------------");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.clearRecipes();
        logger.info("-------------------------------------------");
        logger.info(ChatColor.AQUA + "CraftableTridents - All recipes unregistered!");

        //Plugin shutdown message
        logger.info(ChatColor.AQUA + "CraftableTridents - Plugin By Loving11ish");
        logger.info(ChatColor.AQUA + "CraftableTridents - has been disabled successfully!");
        logger.info("-------------------------------------------");
    }

    public static CraftableTridents getPlugin() {
        return plugin;
    }
}
