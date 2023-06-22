package me.loving11ish.craftabletridents;

import com.rylinaux.plugman.api.PlugManAPI;
import com.tcoded.folialib.FoliaLib;
import io.papermc.lib.PaperLib;
import me.loving11ish.craftabletridents.events.ItemCraftListener;
import me.loving11ish.craftabletridents.files.MessagesFileManager;
import me.loving11ish.craftabletridents.recipes.ElytraRecipe;
import me.loving11ish.craftabletridents.recipes.GodAppleRecipe;
import me.loving11ish.craftabletridents.recipes.TridentRecipe;
import me.loving11ish.craftabletridents.updatesystem.JoinEvent;
import me.loving11ish.craftabletridents.updatesystem.UpdateChecker;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class CraftableTridents extends JavaPlugin {

    public static CraftableTridents plugin;
    public static FoliaLib foliaLib;
    private PluginDescriptionFile pluginInfo = getDescription();
    private String pluginVersion = pluginInfo.getVersion();
    Logger logger = this.getLogger();

    public MessagesFileManager messagesFileManager;

    public ItemStack tridentItem;
    public ItemStack opTridentItem;
    public ItemStack elytraItem;
    public ItemStack enchantedGoldenAppleItem;

    @Override
    public void onEnable() {
        //Plugin startup logic
        plugin = this;
        foliaLib = new FoliaLib(this);

        //Server version compatibility check
        if (!(Bukkit.getServer().getVersion().contains("1.13")||Bukkit.getServer().getVersion().contains("1.14")||
                Bukkit.getServer().getVersion().contains("1.15")||Bukkit.getServer().getVersion().contains("1.16")||
                Bukkit.getServer().getVersion().contains("1.17")||Bukkit.getServer().getVersion().contains("1.18")||
                Bukkit.getServer().getVersion().contains("1.19")||Bukkit.getServer().getVersion().contains("1.20"))){
            logger.warning(ColorUtils.translateColorCodes("&4-------------------------------------------"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &4Your server version is: &d" + Bukkit.getServer().getVersion()));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &4This plugin is only supported on the Minecraft versions listed below:"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.13.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.14.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.15.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.16.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.17.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.18.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.19.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &41.20.x"));
            logger.warning(ColorUtils.translateColorCodes("&6CraftableTridents: &4Is now disabling!"));
            logger.warning(ColorUtils.translateColorCodes("&4-------------------------------------------"));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }else {
            logger.info(ColorUtils.translateColorCodes("&a-------------------------------------------"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aA supported Minecraft version has been detected"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aYour server version is: &d" + Bukkit.getServer().getVersion()));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
            logger.info(ColorUtils.translateColorCodes("&a-------------------------------------------"));
        }

        //Suggest PaperMC if not using
        if (foliaLib.isUnsupported()||foliaLib.isSpigot()){
            PaperLib.suggestPaper(this);
        }

        //Check if PlugManX is enabled
        if (isPlugManXEnabled() || getServer().getPluginManager().isPluginEnabled("PlugManX")){
            if (!PlugManAPI.iDoNotWantToBeUnOrReloaded("CraftableTridents")){
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                logger.severe(ColorUtils.translateColorCodes("&4WARNING WARNING WARNING WARNING!"));
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4You appear to be using an unsupported version of &d&lPlugManX"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4Please &4&lDO NOT USE PLUGMANX TO LOAD/UNLOAD/RELOAD THIS PLUGIN!"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4Please &4&lFULLY RESTART YOUR SERVER!"));
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4This plugin &4&lHAS NOT &4been validated to use this version of PlugManX!"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4&lNo official support will be given to you if you use this!"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4&lUnless Loving11ish has explicitly agreed to help!"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &4Please add CraftableTridents to the ignored-plugins list in PlugManX's config.yml"));
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                logger.severe(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                logger.severe(ColorUtils.translateColorCodes("&c-------------------------------------------"));
            }else {
                logger.info(ColorUtils.translateColorCodes("&a-------------------------------------------"));
                logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aSuccessfully hooked into PlugManX"));
                logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aSuccessfully added CraftableTridents to ignoredPlugins list."));
                logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
                logger.info(ColorUtils.translateColorCodes("&a-------------------------------------------"));
            }
        }else {
            logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &cPlugManX not found!"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &cDisabling PlugManX hook loader"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
            logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
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
        GodAppleRecipe godAppleRecipe = new GodAppleRecipe();
        tridentRecipe.unEnchantedRecipe();
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aStandard Trident Recipe Loaded!"));
        if (getConfig().getBoolean("op-trident.enabled")){
            tridentRecipe.enchantedRecipe();
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aOP Trident Recipe Loaded!"));
        }
        if (getConfig().getBoolean("elytra.enabled")){
            elytraRecipe.elytraRecipe();
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aElytra Recipe Loaded!"));
        }
        if (getConfig().getBoolean("god-apple.enabled")){
            godAppleRecipe.godAppleRecipe();
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aEnchanted Golden Apple Recipe Loaded!"));
        }
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));

        //Register events here
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new ItemCraftListener(), this);

        //Check for available updates
        new UpdateChecker(95032).getVersion(version -> {
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
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &3Plugin by: &b&lLoving11ish"));
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &3has been loaded successfully"));
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &3Plugin Version: &d&l" + pluginVersion));
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
    }

    @Override
    public void onDisable() {
        //Plugin shutdown logic
        Bukkit.clearRecipes();

        //Stop any running tasks
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));
        try {
            foliaLib.getImpl().cancelAllTasks();
            if (foliaLib.isUnsupported()){
                Bukkit.getScheduler().cancelTasks(this);
                logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aAll background task disabled."));
            }
        }catch (Exception e){
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aAll background task disabled."));
        }
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aAll recipes unregistered!"));

        //Plugin shutdown message
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &3Plugin Version: &d&l" + pluginVersion));
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &3Has been shutdown successfully"));
        logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &3Goodbye!"));
        logger.info(ColorUtils.translateColorCodes("-------------------------------------------"));

        //Clear any plugin remains
        tridentItem = null;
        opTridentItem = null;
        elytraItem = null;
        enchantedGoldenAppleItem = null;
        messagesFileManager = null;
        foliaLib = null;
        plugin = null;
    }

    public boolean isPlugManXEnabled() {
        try {
            Class.forName("com.rylinaux.plugman.PlugMan");
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aFound PlugManX main class at:"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &dcom.rylinaux.plugman.PlugMan"));
            return true;
        }catch (ClassNotFoundException e){
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &aCould not find PlugManX main class at:"));
            logger.info(ColorUtils.translateColorCodes("&6CraftableTridents: &dcom.rylinaux.plugman.PlugMan"));
            return false;
        }
    }

    public static CraftableTridents getPlugin() {
        return plugin;
    }

    public static FoliaLib getFoliaLib() {
        return foliaLib;
    }

    public ItemStack getTridentItem() {
        return tridentItem;
    }

    public void setTridentItem(ItemStack tridentItem) {
        this.tridentItem = tridentItem;
    }

    public ItemStack getOpTridentItem() {
        return opTridentItem;
    }

    public void setOpTridentItem(ItemStack opTridentItem) {
        this.opTridentItem = opTridentItem;
    }

    public ItemStack getElytraItem() {
        return elytraItem;
    }

    public void setElytraItem(ItemStack elytraItem) {
        this.elytraItem = elytraItem;
    }

    public ItemStack getEnchantedGoldenAppleItem() {
        return enchantedGoldenAppleItem;
    }

    public void setEnchantedGoldenAppleItem(ItemStack enchantedGoldenAppleItem) {
        this.enchantedGoldenAppleItem = enchantedGoldenAppleItem;
    }
}
