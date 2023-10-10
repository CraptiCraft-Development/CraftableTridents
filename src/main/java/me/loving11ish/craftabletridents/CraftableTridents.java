package me.loving11ish.craftabletridents;

import com.rylinaux.plugman.api.PlugManAPI;
import com.tcoded.folialib.FoliaLib;
import io.papermc.lib.PaperLib;
import me.loving11ish.craftabletridents.events.ItemCraftListener;
import me.loving11ish.craftabletridents.externalhooks.PlugManXAPI;
import me.loving11ish.craftabletridents.files.MessagesFileManager;
import me.loving11ish.craftabletridents.recipes.ElytraRecipe;
import me.loving11ish.craftabletridents.recipes.GodAppleRecipe;
import me.loving11ish.craftabletridents.recipes.TridentRecipe;
import me.loving11ish.craftabletridents.updatesystem.JoinEvent;
import me.loving11ish.craftabletridents.updatesystem.UpdateChecker;
import me.loving11ish.craftabletridents.utils.ColorUtils;
import me.loving11ish.craftabletridents.utils.VersionCheckerUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public final class CraftableTridents extends JavaPlugin {

    ConsoleCommandSender console = Bukkit.getConsoleSender();

    private static CraftableTridents plugin;
    private static FoliaLib foliaLib;
    private static VersionCheckerUtils versionCheckerUtils;
    private PluginDescriptionFile pluginInfo = getDescription();
    private String pluginVersion = pluginInfo.getVersion();

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
        versionCheckerUtils = new VersionCheckerUtils();
        versionCheckerUtils.setVersion();

        //Server version compatibility check
        if (versionCheckerUtils.getVersion() < 13||versionCheckerUtils.getVersion() > 20){
            console.sendMessage(ColorUtils.translateColorCodes("&4-------------------------------------------"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4Your server version is: &d" + Bukkit.getServer().getVersion()));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4This plugin is only supported on the Minecraft versions listed below:"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.13.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.14.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.15.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.16.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.17.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.18.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.19.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &41.20.x"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4Is now disabling!"));
            console.sendMessage(ColorUtils.translateColorCodes("&4-------------------------------------------"));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }else {
            console.sendMessage(ColorUtils.translateColorCodes("&a-------------------------------------------"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aA supported Minecraft version has been detected"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aYour server version is: &d" + Bukkit.getServer().getVersion()));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
            console.sendMessage(ColorUtils.translateColorCodes("&a-------------------------------------------"));
        }

        //Suggest PaperMC if not using
        if (foliaLib.isUnsupported()||foliaLib.isSpigot()){
            PaperLib.suggestPaper(this);
        }

        //Check if PlugManX is enabled
        if (getServer().getPluginManager().isPluginEnabled("PlugManX")|| PlugManXAPI.isPlugManXEnabled()){
            if (!PlugManAPI.iDoNotWantToBeUnOrReloaded("CraftableTridents")){
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&4WARNING WARNING WARNING WARNING!"));
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4You appear to be using an unsupported version of &d&lPlugManX"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4Please &4&lDO NOT USE PLUGMANX TO LOAD/UNLOAD/RELOAD THIS PLUGIN!"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4Please &4&lFULLY RESTART YOUR SERVER!"));
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4This plugin &4&lHAS NOT &4been validated to use this version of PlugManX!"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4&lNo official support will be given to you if you use this!"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4&lUnless Loving11ish has explicitly agreed to help!"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &4Please add CraftableTridents to the ignored-plugins list in PlugManX's config.yml"));
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&c-------------------------------------------"));
            }else {
                console.sendMessage(ColorUtils.translateColorCodes("&a-------------------------------------------"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aSuccessfully hooked into PlugManX"));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aSuccessfully added CraftableTridents to ignoredPlugins list."));
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
                console.sendMessage(ColorUtils.translateColorCodes("&a-------------------------------------------"));
            }
        }else {
            console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &cPlugManX not found!"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &cDisabling PlugManX hook loader"));
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &6Continuing plugin startup"));
            console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));
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
        console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aStandard Trident Recipe Loaded!"));
        if (getConfig().getBoolean("op-trident.enabled")){
            tridentRecipe.enchantedRecipe();
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aOP Trident Recipe Loaded!"));
        }
        if (getConfig().getBoolean("elytra.enabled")){
            elytraRecipe.elytraRecipe();
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aElytra Recipe Loaded!"));
        }
        if (getConfig().getBoolean("god-apple.enabled")){
            godAppleRecipe.godAppleRecipe();
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aEnchanted Golden Apple Recipe Loaded!"));
        }
        console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));

        //Register events here
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new ItemCraftListener(), this);

        //Check for available updates
        new UpdateChecker(95032).getVersion(version -> {
            if (this.getDescription().getVersion().equalsIgnoreCase(version)) {
                console.sendMessage(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("no-update-1")));
                console.sendMessage(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("no-update-2")));
                console.sendMessage(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("no-update-3")));
            }else {
                console.sendMessage(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("update-1")));
                console.sendMessage(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("update-2")));
                console.sendMessage(ColorUtils.translateColorCodes(messagesFileManager.getMessagesConfig().getString("update-3")));
            }
        });

        //Plugin startup message
        console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &3Plugin by: &b&lLoving11ish"));
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &3has been loaded successfully"));
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &3Plugin Version: &d&l" + pluginVersion));
        console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));
    }

    @Override
    public void onDisable() {
        //Plugin shutdown logic
        Bukkit.clearRecipes();

        //Stop any running tasks
        console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));
        try {
            foliaLib.getImpl().cancelAllTasks();
            if (foliaLib.isUnsupported()){
                Bukkit.getScheduler().cancelTasks(this);
                console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aAll background task disabled."));
            }
        }catch (Exception e){
            console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aAll background task disabled."));
        }
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &aAll recipes unregistered!"));

        //Plugin shutdown message
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &3Plugin Version: &d&l" + pluginVersion));
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &3Has been shutdown successfully"));
        console.sendMessage(ColorUtils.translateColorCodes("&6CraftableTridents: &3Goodbye!"));
        console.sendMessage(ColorUtils.translateColorCodes("-------------------------------------------"));

        //Clear any plugin remains
        tridentItem = null;
        opTridentItem = null;
        elytraItem = null;
        enchantedGoldenAppleItem = null;
        messagesFileManager = null;
        foliaLib = null;
        plugin = null;
    }

    public static CraftableTridents getPlugin() {
        return plugin;
    }

    public static FoliaLib getFoliaLib() {
        return foliaLib;
    }

    public static VersionCheckerUtils getVersionCheckerUtils() {
        return versionCheckerUtils;
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
